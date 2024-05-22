package com.rupeshshrestha.usermanagement.service;

import com.rupeshshrestha.usermanagement.dto.AuthenticationRequest;
import com.rupeshshrestha.usermanagement.dto.ServerResponse;

public interface AuthenticationService {
    ServerResponse<?> authenticate(AuthenticationRequest authenticationRequest);
}
