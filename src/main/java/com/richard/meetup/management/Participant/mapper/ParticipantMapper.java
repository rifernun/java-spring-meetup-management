package com.richard.meetup.management.Participant.mapper;

import com.richard.meetup.management.Participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.Participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.Participant.entity.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public static Participant toEntity(ParticipantRequestDto dto) {
        if (dto == null) return null;
        
        Participant participant = new Participant();
        participant.setName(dto.name());
        participant.setEmail(dto.email());
        participant.setLinkedin(dto.linkedin());
        return participant;
    }

    public static ParticipantResponseDto toResponseDto(Participant participant) {
        if (participant == null) return null;

        return new ParticipantResponseDto(
                participant.getId(),
                participant.getName(),
                participant.getEmail(),
                participant.getLinkedin()
        );
    }
}
