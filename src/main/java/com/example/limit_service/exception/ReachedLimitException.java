package com.example.limit_service.exception;

public class ReachedLimitException extends RuntimeException {

    public ReachedLimitException(String message) {
        super(message);
    }
}
