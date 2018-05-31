package de.gmenoiaa.statisticsapi.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.gmenoiaa.statisticsapi.validation.TimestampConstraint;

import java.util.Objects;

public class Transaction {

    private final Double amount;

    @TimestampConstraint
    private final Long timestamp;

    @JsonCreator
    public Transaction(@JsonProperty("amount") Double amount, @JsonProperty("timestamp") Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(amount, timestamp);
    }
}
