package com.richard.meetup.management.participant.service.impl;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.exception.ParticipantAlreadyExists;
import com.richard.meetup.management.participant.exception.ParticipantNotFound;
import com.richard.meetup.management.participant.mapper.ParticipantMapper;
import com.richard.meetup.management.participant.repository.ParticipantRepository;
import com.richard.meetup.management.participant.service.IParticipantService;
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

    private final ParticipantMapper participantMapper;

    public ParticipantServiceImpl(ParticipantMapper participantMapper) {
        this.participantMapper = participantMapper;
    }

    @Override
    public void createParticipant(ParticipantRequestDto participantRequestDto) {
        Participant participant = participantMapper.toEntity(participantRequestDto);
        Optional<Participant> optionalParticipant = repository.findByEmail(participant.getEmail());
        if(optionalParticipant.isPresent()){
            throw new ParticipantAlreadyExists("Participant with the same email already exists");
        } else {
            participant.setCreatedAt(Instant.now());
            participant.setCreatedBy("system"); // change this to actual user in a real application
            repository.save(participant);
        }
    }

    @Override
    public void deleteParticipant(UUID id) {
        if(repository.findById(id).isEmpty()){
            throw new ParticipantNotFound("Participant not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void updateParticipant(ParticipantRequestDto participantRequestDto, UUID id) {
        if(repository.findById(id).isEmpty()){
            throw new ParticipantNotFound("Participant not found with id: " + id);
        }
        Participant participant = repository.getReferenceById(id);
        participant.setName(participantRequestDto.name());
        participant.setEmail(participantRequestDto.email());
        participant.setLinkedin(participantRequestDto.linkedin());
        repository.save(participant);
    }

    @Override
    public ParticipantResponseDto getParticipantById(UUID id) {
        ParticipantResponseDto dto = repository.findById(id)
                .map(participantMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Participant not found with id: " + id));
        return dto;
    }

    @Override
    public List<ParticipantResponseDto> getAllParticipants() {
        List<ParticipantResponseDto> participants = repository.findAll()
                .stream()
                .map(participantMapper::toResponseDto)
                .toList();

        return participants;
    }
}
