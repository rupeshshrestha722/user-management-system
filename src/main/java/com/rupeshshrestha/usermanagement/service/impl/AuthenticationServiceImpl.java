package com.rupeshshrestha.usermanagement.service.impl;

import com.rupeshshrestha.usermanagement.config.JwtUtil;
import com.rupeshshrestha.usermanagement.dto.AuthenticationRequest;
import com.rupeshshrestha.usermanagement.dto.ServerResponse;
import com.rupeshshrestha.usermanagement.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;
    private final JwtUtil jwtUtils;

    @Override
    public ServerResponse<?> authenticate(AuthenticationRequest authenticationRequest) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ServerResponse
                .builder()
                .data(jwt)
                .build();
    }
}
