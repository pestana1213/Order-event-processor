package com.example.order_events_processor.Exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound() {
        super("User not found");
    }
}
