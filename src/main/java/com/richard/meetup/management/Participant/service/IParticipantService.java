package com.richard.meetup.management.Participant.service;

import com.richard.meetup.management.Participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.Participant.dto.ParticipantResponseDto;

import java.util.List;
import java.util.UUID;

public interface IParticipantService {
    void createParticipant(ParticipantRequestDto participantRequestDto);
    void deleteParticipant(UUID id);
    void updateParticipant(ParticipantRequestDto participantRequestDto, UUID id);
    ParticipantResponseDto getParticipantById(UUID id);
    List<ParticipantResponseDto> getAllParticipants();
}
