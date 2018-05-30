package me.geiser.statistics;

import org.springframework.lang.NonNull;

public interface StatisticsService {

    void register(@NonNull Transaction transaction);

    Statistics getStatistics();

}
