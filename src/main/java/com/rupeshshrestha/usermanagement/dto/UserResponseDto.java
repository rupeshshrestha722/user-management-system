package com.rupeshshrestha.usermanagement.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email ;

    private LocalDate dob;

    private Set<String> roles = new HashSet<>();
}
