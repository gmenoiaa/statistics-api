package de.gmenoiaa.statisticsapi.service;

import de.gmenoiaa.statisticsapi.common.Constants;
import de.gmenoiaa.statisticsapi.domain.Statistics;
import de.gmenoiaa.statisticsapi.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final ScheduledExecutorService scheduledPool;
    private final AtomicReference<Statistics> statistics;

    private StatisticsServiceImpl(ScheduledExecutorService scheduledPool, Statistics initialStatistics) {
        this.scheduledPool = scheduledPool;
        this.statistics = new AtomicReference<>(initialStatistics);
    }

    @Autowired
    public StatisticsServiceImpl(ScheduledExecutorService scheduledPool) {
        this(scheduledPool, Statistics.newBuilder().build());
    }

    @Override
    public void registerTransaction(final Transaction transaction) {
        final Long range  = System.currentTimeMillis() - transaction.getTimestamp();
        final Long ttl = Constants.TRANSACTION_TTL_MS - range;

        statistics.getAndUpdate(currentStatistic -> currentStatistic.addTransaction(transaction));
        this.scheduledPool.schedule(
                () -> statistics.getAndUpdate(currentStatistic -> currentStatistic.subtractTransaction(transaction)),
                ttl, TimeUnit.MILLISECONDS);
    }

    @Override
    public Statistics getStatistics() {
        return statistics.get();
    }
}
