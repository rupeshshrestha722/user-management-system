package com.rupeshshrestha.usermanagement.contoller;

import com.rupeshshrestha.usermanagement.converter.UserConverter;
import com.rupeshshrestha.usermanagement.dto.ServerResponse;
import com.rupeshshrestha.usermanagement.dto.UserRequestDto;
import com.rupeshshrestha.usermanagement.dto.UserResponseDto;
import com.rupeshshrestha.usermanagement.entity.User;
import com.rupeshshrestha.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDto userRequestDTO) {

        User user = userService.adduser(userRequestDTO);
        ServerResponse<UserResponseDto> userServerResponse = ServerResponse.<UserResponseDto>builder()
                .success(true)
                .message("User created successfully")
                .data(userConverter.convertToUserResponse(user))
                .build();
        return ResponseEntity.ok(userServerResponse);
    }

    @GetMapping
    public ResponseEntity<ServerResponse<?>> getUsers(Pageable pageable) {
        Page<User> usersPage = userService.findUsers(pageable);
        ServerResponse<?> serverResponse =
                ServerResponse.builder()
                        .success(true)
                        .message("Users fetched successfully")
                        .data(new PageImpl<>(userConverter.convertToUserResponse(usersPage.toList()), pageable, usersPage.getTotalElements()))
                        .build();

        return ResponseEntity.ok(serverResponse);
    }

    @GetMapping("{username}")
    public ResponseEntity<ServerResponse<?>> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userService.findUserByUsername(username);
        ServerResponse<?> serverResponse ;
        if (user.isPresent()){
            serverResponse = ServerResponse.builder()
                    .success(true)
                    .message("User fetched successfully")
                    .data(userConverter.convertToUserResponse(user.get()))
                    .build();
        } else {
            serverResponse = ServerResponse.builder()
                    .success(false)
                    .message("User not found")
                    .build();
        }
        return ResponseEntity.ok(serverResponse);
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<ServerResponse<?>> getUsersByFirstName(@PathVariable("firstName")  String firstName) {
        List<User> users = userService.findUsersByFirstName(firstName);
        ServerResponse<?> serverResponse = ServerResponse.builder()
                .success(true)
                .message("Users fetched successfully")
                .data(userConverter.convertToUserResponse(users))
                .build();
        return ResponseEntity.ok(serverResponse);
    }

    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<ServerResponse<?>> getUsersByLastName(@PathVariable("lastName")  String lastName) {
        List<User> users = userService.findUsersByLastName(lastName);

        ServerResponse<?> serverResponse = ServerResponse.builder()
                .success(true)
                .message("Users fetched successfully")
                .data(userConverter.convertToUserResponse(users))
                .build();
        return ResponseEntity.ok(serverResponse);
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ServerResponse<?>> getUsersByEmail(@PathVariable("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);

        ServerResponse<?> serverResponse;
        if (user.isPresent()) {
            serverResponse = ServerResponse.builder()
                    .success(true)
                    .message("User fetched successfully")
                    .data(userConverter.convertToUserResponse(user.get()))
                    .build();
        } else {
            serverResponse = ServerResponse.builder()
                    .success(false)
                    .message("User not found")
                    .build();
        }
        return ResponseEntity.ok(serverResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserRequestDto userRequestDTO) {
        User user = userService.updateUser(userId, userRequestDTO);
        ServerResponse<UserResponseDto> userServerResponse = ServerResponse.<UserResponseDto>builder()
                .success(true)
                .message("User updated successfully")
                .data(userConverter.convertToUserResponse(user))
                .build();
        return ResponseEntity.ok(userServerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUer(userId);
        ServerResponse<User> userServerResponse = ServerResponse.<User>builder()
                .success(true)
                .message("User deleted successfully")
                .build();
        return ResponseEntity.ok(userServerResponse);
    }
}
