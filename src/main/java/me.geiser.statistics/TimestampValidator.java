package me.geiser.statistics;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimestampValidator implements ConstraintValidator<TimestampConstraint, Date> {

    private static final int SECONDS = 60;

    @Override
    public boolean isValid(Date timestamp, ConstraintValidatorContext constraintValidatorContext) {
        final Date now = new Date();
        final Long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - timestamp.getTime());
        return seconds <= SECONDS;
    }
}
