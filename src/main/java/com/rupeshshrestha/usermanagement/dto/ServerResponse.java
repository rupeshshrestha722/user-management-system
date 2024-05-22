package com.rupeshshrestha.usermanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServerResponse<T> {
    private T data;
    private boolean success;
    private String message;
}
