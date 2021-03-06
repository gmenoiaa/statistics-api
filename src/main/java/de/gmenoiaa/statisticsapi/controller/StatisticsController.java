package de.gmenoiaa.statisticsapi.controller;

import de.gmenoiaa.statisticsapi.domain.Statistics;
import de.gmenoiaa.statisticsapi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public Statistics get() {
        return this.statisticsService.getStatistics();
    }
}
