package me.geiser.statistics;

import java.util.Date;

public class Transaction {

    public Double amount;

    @TimestampConstraint
    public Date timestamp;
}
