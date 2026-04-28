package com.richard.meetup.management.participant.dto;

import java.util.UUID;

public record ParticipantResponseDto(
        UUID id,
        String name,
        String linkedin,
        UUID userId
) {
}
