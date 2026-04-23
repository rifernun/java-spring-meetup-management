package com.richard.meetup.management.enrollment.service.impl;

import com.richard.meetup.management.enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.enrollment.entity.Enrollment;
import com.richard.meetup.management.enrollment.entity.enums.EnrollmentStatus;
import com.richard.meetup.management.enrollment.exception.EnrollmentAlreadyExists;
import com.richard.meetup.management.enrollment.exception.EnrollmentNotFound;
import com.richard.meetup.management.enrollment.mapper.EnrollmentMapper;
import com.richard.meetup.management.enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.enrollment.service.IEnrollmentService;
import com.richard.meetup.management.event.entity.Event;
import com.richard.meetup.management.event.exception.EventNotFound;
import com.richard.meetup.management.event.repository.EventRepository;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.exception.ParticipantNotFound;
import com.richard.meetup.management.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(EnrollmentMapper enrollmentMapper){
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public void createEnrollment(EnrollmentRequestDto enrollmentRequestDto) {
        if (!participantRepository.existsById(enrollmentRequestDto.participantId())) {
            throw new ParticipantNotFound("Participant not found with id: " + enrollmentRequestDto.participantId());
        }

        if (!eventRepository.existsById(enrollmentRequestDto.eventId())) {
            throw new EventNotFound("Event not found with id: " + enrollmentRequestDto.eventId());
        }

        Optional<Enrollment> responseEnrollment = enrollmentRepository.findByParticipantIdAndEventId(enrollmentRequestDto.participantId(), enrollmentRequestDto.eventId());
         if(responseEnrollment.isPresent()){
             if(responseEnrollment.get().getStatus() == EnrollmentStatus.ACTIVE){
                 throw new EnrollmentAlreadyExists("Enrollment already exists for participant id: " + enrollmentRequestDto.participantId() + " and event id: " + enrollmentRequestDto.eventId());
             }
         }

        Participant participant = participantRepository.findById(enrollmentRequestDto.participantId())
                .orElseThrow(() -> new ParticipantNotFound("Participant not found with id: " + enrollmentRequestDto.participantId()));

        Event event = eventRepository.findById(enrollmentRequestDto.eventId())
                .orElseThrow(() -> new EventNotFound("Event not found with id: " + enrollmentRequestDto.eventId()));

        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequestDto, participant, event);
        enrollment.setCreatedBy("system");
        enrollmentRepository.save(enrollment);
    }

    @Override
    public void cancelEnrollment(UUID id) {
        if(enrollmentRepository.findById(id).isEmpty()){
            throw new EnrollmentNotFound("Enrollment not found with id: " + id);
        }
        Enrollment enrollment = enrollmentRepository.getReferenceById(id);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        enrollmentRepository.save(enrollment);
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(UUID id) {
        EnrollmentResponseDto dto = enrollmentRepository.findById(id)
                .map(enrollmentMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
        return dto;
    }

    @Override
    public List<EnrollmentResponseDto> getAllEnrollments() {
        List<EnrollmentResponseDto> list = enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponseDto)
                .toList();

        return list;
    }
}