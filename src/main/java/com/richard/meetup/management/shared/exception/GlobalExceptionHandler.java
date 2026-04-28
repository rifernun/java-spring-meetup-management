package com.richard.meetup.management.shared.exception;

import com.richard.meetup.management.enrollment.exception.EnrollmentAlreadyExists;
import com.richard.meetup.management.enrollment.exception.EnrollmentNotFound;
import com.richard.meetup.management.event.exception.EventAlreadyExists;
import com.richard.meetup.management.event.exception.EventNotFound;
import com.richard.meetup.management.participant.exception.ParticipantAlreadyExists;
import com.richard.meetup.management.participant.exception.ParticipantNotFound;
import com.richard.meetup.management.shared.dto.ErrorResponseDto;
import com.richard.meetup.management.user.exception.UserAlreadyExistsException;
import com.richard.meetup.management.user.exception.UserIsAlreadyAnAdminException;
import com.richard.meetup.management.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(ParticipantAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleParticipantAlreadyExistsException(ParticipantAlreadyExists ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParticipantNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleParticipantNotFoundException(ParticipantNotFound ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleEventAlreadyExistsException(EventAlreadyExists ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleEventNotFoundException(EventNotFound ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EnrollmentAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> handleEnrollmentAlreadyExistsException(EnrollmentAlreadyExists ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnrollmentNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleEnrollmentNotFoundException(EnrollmentNotFound ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsAlreadyAnAdminException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserIsAlreadyAnAdminException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }
}