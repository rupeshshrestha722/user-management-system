package com.rupeshshrestha.usermanagement.validator;

import com.rupeshshrestha.usermanagement.constraint.UsernameConstraint;
import com.rupeshshrestha.usermanagement.entity.User;
import com.rupeshshrestha.usermanagement.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UsernameConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if(null == username || username.isBlank()) {
            return true;
        }
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        return userOptional.isEmpty();
    }
}
