package com.rupeshshrestha.usermanagement.validator;

import com.rupeshshrestha.usermanagement.constraint.EmailConstraint;
import com.rupeshshrestha.usermanagement.entity.User;
import com.rupeshshrestha.usermanagement.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailvalidator implements ConstraintValidator<EmailConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (null == email || email.isBlank()) {
            return true;
        }
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.isEmpty();
    }
}