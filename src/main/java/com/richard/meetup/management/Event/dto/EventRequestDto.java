package com.richard.meetup.management.Event.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.time.LocalDateTime;

public record EventRequestDto(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull @Future LocalDateTime dateTime,
        @NotBlank String location,
        @Positive Long maxCapacity
) {}