package com.richard.meetup.management.Participant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ParticipantRequestDto(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String linkedin
) {}
