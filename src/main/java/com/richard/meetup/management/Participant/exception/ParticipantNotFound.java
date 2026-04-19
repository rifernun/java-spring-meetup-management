package com.richard.meetup.management.Participant.exception;

public class ParticipantNotFound extends RuntimeException {
    public ParticipantNotFound(String message) {
        super(message);
    }
}
