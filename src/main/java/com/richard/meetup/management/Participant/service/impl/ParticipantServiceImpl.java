package com.richard.meetup.management.Participant.service.impl;

import com.richard.meetup.management.Participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.Participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.Participant.entity.Participant;
import com.richard.meetup.management.Participant.mapper.ParticipantMapper;
import com.richard.meetup.management.Participant.repository.ParticipantRepository;
import com.richard.meetup.management.Participant.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantServiceImpl implements IParticipantService {

    @Autowired
    private ParticipantRepository repository;

    @Override
    public void createParticipant(ParticipantRequestDto participantRequestDto) {
        Participant participant = ParticipantMapper.toEntity(participantRequestDto);
        Optional<Participant> optionalParticipant = repository.findByEmail(participant.getEmail());
        if(optionalParticipant.isPresent()){
            throw new RuntimeException("Participant with the same email already exists");
        } else {
            participant.setCreatedAt(Instant.now());
            participant.setCreatedBy("system"); // In a real application, this should be the authenticated user
            repository.save(participant);
        }
    }

    @Override
    public void deleteParticipant(UUID id) {

    }

    @Override
    public void updateParticipant(ParticipantResponseDto participantResponseDto) {

    }

    @Override
    public ParticipantResponseDto getParticipantById(UUID id) {
        ParticipantResponseDto dto = repository.findById(id)
                .map(ParticipantMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Participant not found with id: " + id));
        return dto;
    }

    @Override
    public List<ParticipantResponseDto> getAllParticipants() {
        List<ParticipantResponseDto> participants = repository.findAll()
                .stream()
                .map(ParticipantMapper::toResponseDto)
                .toList();

        return participants;
    }
}
