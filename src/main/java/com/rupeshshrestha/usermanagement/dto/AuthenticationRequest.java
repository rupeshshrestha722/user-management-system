package com.rupeshshrestha.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}

