package com.github.biswajitkrpaul.authservice.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode().value(), ex.getReason(), Instant.now());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private int statusCode;
        private String message;
        private Instant timestamp;
    }
}