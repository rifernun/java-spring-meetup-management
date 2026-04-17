package com.richard.meetup.management.Event.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dateTime,
        String location,
        Long maxCapacity,
        Long remainingSpots //calcular no backend, nao existe na table!
) {
}
