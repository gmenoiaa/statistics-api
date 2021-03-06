package de.gmenoiaa.statisticsapi.controller;

import de.gmenoiaa.statisticsapi.domain.Transaction;
import de.gmenoiaa.statisticsapi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TransactionController {

    private StatisticsService statisticsService;

    @Autowired
    public TransactionController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> post(
            @RequestBody @Valid Transaction transaction,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        this.statisticsService.registerTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
