package com.richard.meetup.management.Enrollment.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record EnrollmentRequestDto(
        @NotNull(message = "Participant ID is required")
        UUID participantId,

        @NotNull(message = "Event ID is required")
        UUID eventId
) {}
