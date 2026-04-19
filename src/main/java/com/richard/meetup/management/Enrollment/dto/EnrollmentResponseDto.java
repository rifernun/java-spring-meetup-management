package com.richard.meetup.management.Enrollment.dto;

import com.richard.meetup.management.Enrollment.entity.enums.EnrollmentStatus;
import com.richard.meetup.management.Event.dto.EventResponseDto;
import com.richard.meetup.management.Participant.dto.ParticipantResponseDto;

import java.time.Instant;
import java.util.UUID;

public record EnrollmentResponseDto(
        UUID id,
        ParticipantResponseDto participant,
        EventResponseDto event,
        Instant dateTime,
        EnrollmentStatus status
) {}
