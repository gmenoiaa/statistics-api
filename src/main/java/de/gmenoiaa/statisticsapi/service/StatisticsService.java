package de.gmenoiaa.statisticsapi.service;

import de.gmenoiaa.statisticsapi.domain.Statistics;
import de.gmenoiaa.statisticsapi.domain.Transaction;
import org.springframework.lang.NonNull;

public interface StatisticsService {

    void registerTransaction(@NonNull Transaction transaction);

    Statistics getStatistics();

}
