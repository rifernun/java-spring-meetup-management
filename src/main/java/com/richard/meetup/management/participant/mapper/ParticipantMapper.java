package com.richard.meetup.management.participant.mapper;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.user.entity.User;
import com.richard.meetup.management.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    public ParticipantResponseDto toResponseDto(Participant participant) {
        if (participant == null) return null;

        return new ParticipantResponseDto(
                participant.getId(),
                participant.getName(),
                participant.getLinkedin(),
                participant.getUser().getId()
        );
    }
}
