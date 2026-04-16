package com.richard.meetup.management.Event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public record EventRequestDto(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull @Future Instant dateTime,
        @NotBlank String location,
        @Positive Long maxCapacity
) {}