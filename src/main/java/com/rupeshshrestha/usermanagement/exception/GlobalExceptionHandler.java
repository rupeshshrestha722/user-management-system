package com.rupeshshrestha.usermanagement.exception;

import com.rupeshshrestha.usermanagement.dto.ServerResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex, HttpServletRequest request) {

        Map<String, ArrayList<String>> errors = new HashMap<>();
        ArrayList<String> errorMessages;
        /* Handling field level Error */
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            if (errors.containsKey(fieldError.getField().toLowerCase())) {
                errorMessages = errors.get(fieldError.getField().toLowerCase());
                errorMessages.add(fieldError.getDefaultMessage());
                errors.replace(fieldError.getField().toLowerCase(), errorMessages);
            } else {
                errors.put(fieldError.getField().toLowerCase(), new ArrayList<>(Set.of(fieldError.getDefaultMessage())));
            }
        }

        /* Handling class level error */
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            if (errors.containsKey(objectError.getCode().toLowerCase())) {
                errorMessages = errors.get(objectError.getCode().toLowerCase());
                errorMessages.add(objectError.getDefaultMessage());
                errors.replace(objectError.getCode().toLowerCase(), errorMessages);
            } else {
                errors.put(objectError.getCode().toLowerCase(), new ArrayList<>(Set.of(objectError.getDefaultMessage())));
            }
        }
        return new ResponseEntity<>(buildErrorResponse("Invalid Data", errors), BAD_REQUEST);
    }

    private ServerResponse<?> buildErrorResponse(String message, Object object) {
        return ServerResponse
                .builder()
                .success(false)
                .data(object)
                .message(message)
                .build();
    }

    @ExceptionHandler
    public ResponseEntity<?> handleBadRequest(BadrequestException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorResponse(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleServerConfigException(ServerConfigException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorResponse(ex.getMessage()), BAD_REQUEST);
    }

    private ServerResponse<?> buildErrorResponse(String message) {
        return ServerResponse
                .builder()
                .success(false)
                .message(message)
                .build();
    }
}
