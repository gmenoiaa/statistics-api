package de.gmenoiaa.statisticsapi.validation;

import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;

public class TimestampValidatorTest {

    @MockBean
    private ConstraintValidatorContext context;
    private TimestampValidator validator;

    @Before
    public void setup() {
        this.validator = new TimestampValidator();
    }

    @Test
    public void checkIfTimestampIsValid() {
        boolean result = validator.isValid(DateUtil.now().getTime(), context);
        assertThat(result).isTrue();
    }

    @Test
    public void checkIfTimestampIsNotValid() {
        boolean result = validator.isValid(DateUtil.yesterday().getTime(), context);
        assertThat(result).isFalse();
    }
}
