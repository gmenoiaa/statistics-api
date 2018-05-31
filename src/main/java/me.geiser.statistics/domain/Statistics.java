package me.geiser.statistics.domain;

import java.util.Objects;

public class Statistics {

    private final Long count;
    private final Double sum;
    private final Double max;
    private final Double min;

    public Statistics(Long count, Double sum, Double max, Double min) {
        this.count = count;
        this.sum = sum;
        this.max = max;
        this.min = min;
    }

    public Long getCount() {
        return count;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getAvg() {
        return count > 0 ? sum / count : null;
    }

    public Double getSum() {
        return sum;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(count, that.count) &&
                Objects.equals(sum, that.sum) &&
                Objects.equals(max, that.max) &&
                Objects.equals(min, that.min);
    }

    @Override
    public int hashCode() {

        return Objects.hash(count, sum, max, min);
    }

    public static class Builder {
        private Long count = 0l;
        private Double sum = 0d;
        private Double max;
        private Double min;

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public Builder sum(Double sum) {
            this.sum = sum;
            return this;
        }

        public Builder max(Double max) {
            this.max = max;
            return this;
        }

        public Builder min(Double min) {
            this.min = min;
            return this;
        }

        public Statistics build() {
            return new Statistics(count, sum, max, min);
        }
    }
}
