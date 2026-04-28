package com.richard.meetup.management.participant.service.impl;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.exception.ParticipantNotFound;
import com.richard.meetup.management.participant.mapper.ParticipantMapper;
import com.richard.meetup.management.participant.repository.ParticipantRepository;
import com.richard.meetup.management.participant.service.IParticipantService;
import com.richard.meetup.management.user.entity.User;
import com.richard.meetup.management.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final UserRepository userRepository;

    public ParticipantServiceImpl(ParticipantMapper participantMapper, UserRepository userRepository) {
        this.participantMapper = participantMapper;
        this.userRepository = userRepository;
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
        if(id == null) {
            updateMe(participantRequestDto);
            return;
        }
        if(repository.findById(id).isEmpty()){
            throw new ParticipantNotFound("Participant not found with id: " + id);
        }
        Participant participant = repository.getReferenceById(id);
        participant.setName(participantRequestDto.name());
        participant.setLinkedin(participantRequestDto.linkedin());
        repository.save(participant);
    }

    private void updateMe(ParticipantRequestDto data){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Participant> optionalParticipant = repository.findByUserEmail(email);
        if(optionalParticipant.isEmpty()){
            throw new ParticipantNotFound("Participant not found with email: " + email);
        }
        Participant participant = optionalParticipant.get();
        participant.setName(data.name());
        participant.setLinkedin(data.linkedin());
        participant.setUpdatedAt(Instant.now());
        repository.save(participant);
    }

    @Override
    public ParticipantResponseDto getParticipantById(UUID id) {
        ParticipantResponseDto dto = repository.findById(id)
                .map(participantMapper::toResponseDto)
                .orElseThrow(() -> new ParticipantNotFound("Participant not found with id: " + id));
        return dto;
    }

    @Override
    public ParticipantResponseDto getParticipantByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ParticipantNotFound("Participant not found with email: " + email));
        ParticipantResponseDto dto = repository.findByUser(user)
                .map(participantMapper::toResponseDto)
                .orElseThrow(() -> new ParticipantNotFound("Participant not found with id: " + user.getId()));
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
