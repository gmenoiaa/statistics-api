package de.gmenoiaa.statisticsapi.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {TimestampValidator.class})
public @interface TimestampConstraint {

    String message() default "Timestamp is not valid anymore";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
