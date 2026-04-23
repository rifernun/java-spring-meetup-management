package com.richard.meetup.management.participant.exception;

public class ParticipantNotFound extends RuntimeException {
    public ParticipantNotFound(String message) {
        super(message);
    }
}
