package com.richard.meetup.management.Enrollment.service.impl;

import com.richard.meetup.management.Enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.Enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.Enrollment.mapper.EnrollmentMapper;
import com.richard.meetup.management.Enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.Enrollment.service.IEnrollmentService;
import com.richard.meetup.management.Event.entity.Event;
import com.richard.meetup.management.Event.repository.EventRepository;
import com.richard.meetup.management.Participant.entity.Participant;
import com.richard.meetup.management.Participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public void createEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        if (!participantRepository.existsById(enrollmentRequestDto.participantId())) {
            throw new RuntimeException("Participant not found with id: " + enrollmentRequestDto.participantId());
        }

        if (!eventRepository.existsById(enrollmentRequestDto.eventId())) {
            throw new RuntimeException("Event not found with id: " + enrollmentRequestDto.eventId());
        }

        if (enrollmentRepository.existsByParticipantIdAndEventId(enrollmentRequestDto.participantId(), enrollmentRequestDto.eventId())) {
            throw new RuntimeException("Enrollment already exists for participant and event");
        }

        Participant participant = participantRepository.findById(enrollmentRequestDto.participantId())
                .orElseThrow(() -> new RuntimeException("Participant not found with id: " + enrollmentRequestDto.participantId()));

        Event event = eventRepository.findById(enrollmentRequestDto.eventId())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + enrollmentRequestDto.eventId()));

        var enrollment = EnrollmentMapper.toEntity(enrollmentRequestDto);
        enrollment.setParticipant(participant);
        enrollment.setEvent(event);
        enrollment.setCreatedBy("system");
        enrollmentRepository.save(enrollment);
    }

    @Override
    public void deleteEnrollment(UUID id) {
        //TODO
    }

    @Override
    public void updateEnrollment(EnrollmentResponseDto enrollmentResponseDto) {
        //TODO
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(UUID id) {
        EnrollmentResponseDto dto = enrollmentRepository.findById(id)
                .map(EnrollmentMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        return dto;
    }

    @Override
    public List<EnrollmentResponseDto> getAllEnrollments() {
        List<EnrollmentResponseDto> list = enrollmentRepository.findAll()
                .stream()
                .map(EnrollmentMapper::toResponseDto)
                .toList();

        return list;
    }
}
