package com.assignment.conference.validator.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { DateValidator.class })
public @interface DateValid {

	String value();

	String message() default "{InvalidDate.validation.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}