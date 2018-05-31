package me.geiser.statistics.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.TimeUnit;

public class TimestampValidator implements ConstraintValidator<TimestampConstraint, Long> {

    private static final long MILLISECONDS = TimeUnit.SECONDS.toMillis(60);

    @Override
    public boolean isValid(Long timestamp, ConstraintValidatorContext constraintValidatorContext) {
        return System.currentTimeMillis() - timestamp <= MILLISECONDS;
    }
}
