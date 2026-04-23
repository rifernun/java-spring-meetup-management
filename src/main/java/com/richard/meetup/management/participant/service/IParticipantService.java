package com.richard.meetup.management.participant.service;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;

import java.util.List;
import java.util.UUID;

public interface IParticipantService {
    void createParticipant(ParticipantRequestDto participantRequestDto);
    void deleteParticipant(UUID id);
    void updateParticipant(ParticipantRequestDto participantRequestDto, UUID id);
    ParticipantResponseDto getParticipantById(UUID id);
    List<ParticipantResponseDto> getAllParticipants();
}
