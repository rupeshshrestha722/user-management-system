package com.rupeshshrestha.usermanagement.service;

import com.rupeshshrestha.usermanagement.dto.UserRequestDto;
import com.rupeshshrestha.usermanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User adduser(UserRequestDto userRequestDto);

    List<User> findUsersByFirstName(String firstName);

    List<User> findUsersByLastName(String lastName);

    Page<User> findUsers(Pageable pageable);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    User updateUser(Long id, UserRequestDto userRequestDTO);

    void deleteUer(Long id);




}
