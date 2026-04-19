package com.richard.meetup.management.Enrollment.exception;

public class EnrollmentAlreadyExists extends RuntimeException {
    public EnrollmentAlreadyExists(String message) {
        super(message);
    }
}
