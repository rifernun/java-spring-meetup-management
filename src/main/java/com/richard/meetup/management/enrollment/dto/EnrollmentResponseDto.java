package com.richard.meetup.management.enrollment.dto;

import com.richard.meetup.management.enrollment.entity.enums.EnrollmentStatus;
import com.richard.meetup.management.event.dto.EventResponseDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;

import java.time.Instant;
import java.util.UUID;

public record EnrollmentResponseDto(
        UUID id,
        ParticipantResponseDto participant,
        EventResponseDto event,
        Instant dateTime,
        EnrollmentStatus status
) {}
