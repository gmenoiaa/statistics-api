package me.geiser.statistics.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsTest {

    @Test
    public void checkAllFields() {
        Statistics statistics = Statistics.newBuilder()
            .count(2l)
            .sum(20d)
            .min(5d)
            .max(15d)
            .build();
        assertThat(statistics.getAvg()).isEqualTo(10d);
        assertThat(statistics.getCount()).isEqualTo(2l);
        assertThat(statistics.getSum()).isEqualTo(20d);
        assertThat(statistics.getMin()).isEqualTo(5d);
        assertThat(statistics.getMax()).isEqualTo(15d);
    }

    @Test
    public void checkNoFieldSet() {
        Statistics statistics = Statistics.newBuilder().build();
        assertThat(statistics.getAvg()).isNull();
        assertThat(statistics.getCount()).isZero();
        assertThat(statistics.getSum()).isZero();
        assertThat(statistics.getMin()).isNull();
        assertThat(statistics.getMax()).isNull();
    }
}
