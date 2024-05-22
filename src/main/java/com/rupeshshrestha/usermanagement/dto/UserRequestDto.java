package com.rupeshshrestha.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Username i required")
    @Size(min = 5, max = 15, message = "Username should be minimum of 5 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max= 20, message = "Password should be 8-20 characters")
    private String password;

    @NotBlank(message = "Firstname is required")
    @Size(min = 1, message = "Firstname should be minimum of 1 characters")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    @Size(min = 1, message = "Lastname should be minimum of 1 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid email")
    private String email;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of Birth must be past in date")
    private LocalDate dob;
}
