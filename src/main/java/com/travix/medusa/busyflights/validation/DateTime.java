package com.travix.medusa.busyflights.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTime {
    String message() default "{validation.datetime.format.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    DateTimeFormat format() default DateTimeFormat.ISO_LOCAL_DATE;
}
