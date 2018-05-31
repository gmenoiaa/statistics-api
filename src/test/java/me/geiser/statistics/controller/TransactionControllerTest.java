package me.geiser.statistics.controller;


import me.geiser.statistics.domain.Transaction;
import me.geiser.statistics.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void checkTransactionCreated() throws Exception {
        Double amount = 5.1;
        Long timestamp = System.currentTimeMillis();

        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content(String.format("{\"amount\":%s,\"timestamp\":%d}", amount, timestamp)))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        verify(statisticsService).registerTransaction(new Transaction(amount, timestamp));
    }

    @Test
    public void checkTransactionNotCreated() throws Exception {
        Double amount = 2.1;
        Long timestamp = 1478192204000L;

        mvc.perform(post("/transactions")
                .contentType("application/json")
                .content(String.format("{\"amount\":%s,\"timestamp\":%d}", amount, timestamp)))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(statisticsService, never()).registerTransaction(any());
    }

}
