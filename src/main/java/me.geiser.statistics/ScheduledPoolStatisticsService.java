package me.geiser.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.*;

//@Service
public class ScheduledPoolStatisticsService implements StatisticsService {

    private ScheduledExecutorService scheduledPool;
    private Statistics statistics = new Statistics();

    @Autowired
    public ScheduledPoolStatisticsService(ScheduledExecutorService scheduledPool) {
        this.scheduledPool = scheduledPool;
    }

    @Override
    public void register(Transaction transaction) {
        Long range  = new Date().getTime() - transaction.timestamp.getTime();
        Long ttl = TimeUnit.SECONDS.toMillis(60) - range;

        System.out.println("ttl=" + ttl);

        Double oldMax = statistics.max;
        Double oldMin = statistics.min;
        statistics.count++;
        statistics.sum += transaction.amount;
        if (statistics.max == null) {
            statistics.max = transaction.amount;
        }
        else {
            statistics.max = transaction.amount > statistics.max ? transaction.amount : statistics.max;
        }
        if(statistics.min == null) {
            statistics.min = transaction.amount;
        }
        else {
            statistics.min = transaction.amount < statistics.min ? transaction.amount : statistics.min;
        }
        statistics.avg = statistics.sum / statistics.count;
        this.scheduledPool.schedule(() -> {

            System.out.println("removing transaction");

            statistics.count--;
            statistics.sum -= transaction.amount;
            statistics.avg = statistics.sum / statistics.count;
            statistics.max = oldMax > transaction.amount ? oldMax : statistics.max;
            statistics.min = oldMin > transaction.amount ? oldMin : statistics.min;
        }, ttl, TimeUnit.MILLISECONDS);
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }
}
