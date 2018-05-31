package me.geiser.statistics.domain;

import org.assertj.core.util.DateUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {

    @Test
    public void checkIfFieldsAreSet() {
        Transaction transaction = new Transaction(15.00, System.currentTimeMillis());
        assertThat(transaction.getAmount()).isEqualTo(15.00);
        assertThat(transaction.getTimestamp()).isNotNull();
    }
}
