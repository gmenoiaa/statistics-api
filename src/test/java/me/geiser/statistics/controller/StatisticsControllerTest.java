package me.geiser.statistics.controller;

import me.geiser.statistics.domain.Statistics;
import me.geiser.statistics.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void checkResponseWithoutStatistics() throws Exception{
        when(statisticsService.getStatistics()).thenReturn(Statistics.newBuilder().build());
        mvc.perform(get("/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(0)))
                .andExpect(jsonPath("sum", is(0.0)))
                .andExpect(jsonPath("avg", nullValue()))
                .andExpect(jsonPath("max", nullValue()))
                .andExpect(jsonPath("min", nullValue()));
    }

    @Test
    public void checkResponseWithStatistics() throws Exception{
        when(statisticsService.getStatistics()).thenReturn(Statistics.newBuilder()
                .count(2L)
                .sum(20.0)
                .min(5.0)
                .max(15.0)
                .build());

        mvc.perform(get("/statistics").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("count", is(2)))
                .andExpect(jsonPath("sum", is(20.0)))
                .andExpect(jsonPath("avg", is(10.0)))
                .andExpect(jsonPath("max", is(15.0)))
                .andExpect(jsonPath("min", is(5.0)));
    }
}
