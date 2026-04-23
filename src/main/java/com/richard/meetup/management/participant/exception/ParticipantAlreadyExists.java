package com.richard.meetup.management.participant.exception;

public class ParticipantAlreadyExists extends RuntimeException {
    public ParticipantAlreadyExists(String message) {
        super(message);
    }
}
