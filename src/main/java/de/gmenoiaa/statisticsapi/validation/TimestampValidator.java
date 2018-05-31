package de.gmenoiaa.statisticsapi.validation;

import de.gmenoiaa.statisticsapi.common.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.TimeUnit;

public class TimestampValidator implements ConstraintValidator<TimestampConstraint, Long> {

    @Override
    public boolean isValid(Long timestamp, ConstraintValidatorContext constraintValidatorContext) {
        Long now = System.currentTimeMillis();
        return timestamp <= now && now - timestamp <= Constants.TRANSACTION_TTL_MS;
    }
}
