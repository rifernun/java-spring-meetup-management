package com.richard.meetup.management.Enrollment.dto;

import java.time.Instant;
import java.util.UUID;

public record EnrollmentResponseDto(
        UUID id,
        UUID participantId,
        UUID eventId,
        Instant dateTime
) {}
