package com.assignment.conference.validator.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<DateValid, String> {

    private DateTimeFormatter dateTimeFormatter;

    @Override
    public void initialize(DateValid constraintAnnotation) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            this.dateTimeFormatter.parse(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
