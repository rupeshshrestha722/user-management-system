package com.rupeshshrestha.usermanagement.constraint;

import com.rupeshshrestha.usermanagement.validator.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UsernameConstraint {
    String message() default "Username is already registered";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
