package com.richard.meetup.management.Participant.exception;

public class ParticipantAlreadyExists extends RuntimeException {
    public ParticipantAlreadyExists(String message) {
        super(message);
    }
}
