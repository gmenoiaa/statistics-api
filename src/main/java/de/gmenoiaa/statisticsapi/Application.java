package de.gmenoiaa.statisticsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@SpringBootApplication
public class Application {

    private static final int POOL_SIZE = 10;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ScheduledExecutorService provideScheduledPool() {
        return Executors.newScheduledThreadPool(POOL_SIZE);
    }
}