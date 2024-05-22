package com.rupeshshrestha.usermanagement.constraint;

import com.rupeshshrestha.usermanagement.validator.UniqueEmailvalidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueEmailvalidator.class)
public @interface EmailConstraint {

    String message() default "Email is already registered";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}