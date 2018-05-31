package me.geiser.statistics.service;

import me.geiser.statistics.domain.Statistics;
import me.geiser.statistics.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private ScheduledExecutorService scheduledPool;
    private AtomicReference<Statistics> statistics =
            new AtomicReference<Statistics>(Statistics.newBuilder().build());

    @Autowired
    public StatisticsServiceImpl(ScheduledExecutorService scheduledPool) {
        this.scheduledPool = scheduledPool;
    }

    @Override
    public void registerTransaction(final Transaction transaction) {
        final Long range  = System.currentTimeMillis() - transaction.getTimestamp();
        final Long ttl = TimeUnit.SECONDS.toMillis(60) - range;

        statistics.getAndUpdate(currentStatistic -> {
            Statistics.Builder builder = Statistics.newBuilder()
                    .count(currentStatistic.getCount() + 1)
                    .sum(currentStatistic.getSum() + transaction.getAmount());

            if (currentStatistic.getMax() == null || transaction.getAmount() > currentStatistic.getMax()) {
                builder.max(transaction.getAmount());
            }

            if (currentStatistic.getMin() == null || transaction.getAmount() > currentStatistic.getMin()) {
                builder.min(transaction.getAmount());
            }

            return builder.build();
        });

        this.scheduledPool.schedule(() -> {
            statistics.getAndUpdate(currentStatistic -> {
                Statistics.Builder builder = Statistics.newBuilder()
                        .count(currentStatistic.getCount() - 1)
                        .sum(currentStatistic.getSum() - transaction.getAmount());

                // TODO revert max/min

                return builder.build();
            });
        }, ttl, TimeUnit.MILLISECONDS);
    }

    @Override
    public Statistics getStatistics() {
        return statistics.get();
    }
}
