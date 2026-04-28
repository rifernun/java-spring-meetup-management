package com.richard.meetup.management.participant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ParticipantRequestDto(
        @NotBlank String name,
        @NotBlank String linkedin,
        @NotBlank UUID userId
) {}
