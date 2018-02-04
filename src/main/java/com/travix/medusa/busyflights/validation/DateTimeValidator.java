package com.travix.medusa.busyflights.validation;

import java.time.LocalDate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

    private DateTime constraint;

    public void initialize(DateTime constraint) {
        this.constraint = constraint;
    }

    public boolean isValid(String dateTimeStr, ConstraintValidatorContext context) {
        try {
            LocalDate.parse(dateTimeStr, constraint.format().getDateTimeFormatter());
            return true;
        } catch (Throwable t) {
            return false;
        }
    }


}
