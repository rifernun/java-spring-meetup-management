package com.richard.meetup.management.Event.exception;

public class EventAlreadyExists extends RuntimeException {
    public EventAlreadyExists(String message) {
        super(message);
    }
}
