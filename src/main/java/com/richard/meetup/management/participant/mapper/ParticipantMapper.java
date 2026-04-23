package com.richard.meetup.management.participant.mapper;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.entity.Participant;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public Participant toEntity(ParticipantRequestDto dto) {
        if (dto == null) return null;
        
        Participant participant = new Participant();
        participant.setName(dto.name());
        participant.setEmail(dto.email());
        participant.setLinkedin(dto.linkedin());
        return participant;
    }

    public ParticipantResponseDto toResponseDto(Participant participant) {
        if (participant == null) return null;

        return new ParticipantResponseDto(
                participant.getId(),
                participant.getName(),
                participant.getEmail(),
                participant.getLinkedin()
        );
    }
}
