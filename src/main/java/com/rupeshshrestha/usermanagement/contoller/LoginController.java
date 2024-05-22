package com.rupeshshrestha.usermanagement.contoller;

import com.rupeshshrestha.usermanagement.constant.AppConstant;
import com.rupeshshrestha.usermanagement.dto.AuthenticationRequest;
import com.rupeshshrestha.usermanagement.dto.ServerResponse;
import com.rupeshshrestha.usermanagement.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> doLogin(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        ServerResponse<?> response = authenticationService.authenticate(authenticationRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AppConstant.AUTHORIZATION_HEADER, "Bearer " + response.getData());
        return new ResponseEntity<>(
                ServerResponse.builder().success(true)
                        .message("Login Successful")
                        .data("Bearer " + response.getData())
                        .build(),
                httpHeaders,
                HttpStatus.OK);
    }
}
