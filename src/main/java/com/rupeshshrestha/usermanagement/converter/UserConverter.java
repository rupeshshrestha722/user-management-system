package com.rupeshshrestha.usermanagement.converter;

import com.rupeshshrestha.usermanagement.dto.UserResponseDto;
import com.rupeshshrestha.usermanagement.entity.Role;
import com.rupeshshrestha.usermanagement.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public List<UserResponseDto>  convertToUserResponse(List<User> userList) {
         return userList.stream()
                 .map(this::convertToUserResponse)
                 .toList();
    }

    public UserResponseDto convertToUserResponse(User user) {
        return UserResponseDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dob(user.getDob())
                .roles(user.getRole().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }

}
