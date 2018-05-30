package me.geiser.statistics;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CacheStatisticsService implements StatisticsService {

    private Cache<String, Transaction> cache;
    private Statistics statistics = new Statistics();

    public CacheStatisticsService() {
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(60))
                .removalListener(listener -> {
                    statistics.count--;
                    statistics.sum -= transaction.amount;
                    statistics.avg = statistics.sum / statistics.count;
                    statistics.max = oldMax > transaction.amount ? oldMax : statistics.max;
                    statistics.min = oldMin > transaction.amount ? oldMin : statistics.min;
                })
                .build();
    }

    @Override
    public void register(Transaction transaction) {
        this.cache.put(UUID.randomUUID().toString(), transaction);
    }

    @Override
    public Statistics getStatistics() {
        return null;
    }
}
