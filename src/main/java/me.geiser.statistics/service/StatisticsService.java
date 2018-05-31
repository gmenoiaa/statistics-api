package me.geiser.statistics.service;

import me.geiser.statistics.domain.Statistics;
import me.geiser.statistics.domain.Transaction;
import org.springframework.lang.NonNull;

public interface StatisticsService {

    void registerTransaction(@NonNull Transaction transaction);

    Statistics getStatistics();

}
