package com.richard.meetup.management.enrollment.exception;

public class EnrollmentAlreadyExists extends RuntimeException {
    public EnrollmentAlreadyExists(String message) {
        super(message);
    }
}
