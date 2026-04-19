package com.richard.meetup.management.Enrollment.mapper;

import com.richard.meetup.management.Enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.Enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.Enrollment.entity.Enrollment;
import com.richard.meetup.management.Enrollment.service.impl.EnrollmentServiceImpl;
import com.richard.meetup.management.Event.entity.Event;
import com.richard.meetup.management.Event.mapper.EventMapper;
import com.richard.meetup.management.Event.service.impl.EventServiceImpl;
import com.richard.meetup.management.Participant.entity.Participant;
import com.richard.meetup.management.Participant.mapper.ParticipantMapper;

import com.richard.meetup.management.shared.utils.CountRemainingSpots;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@AllArgsConstructor
public class EnrollmentMapper {

    private final EventMapper eventMapper;
    private final ParticipantMapper participantMapper;
    private final CountRemainingSpots countRemainingSpots;

    public Enrollment toEntity(EnrollmentRequestDto enrollmentRequestDto, Participant participant, Event event) {
        if (enrollmentRequestDto == null) return null;

        Enrollment enrollment = new Enrollment();
        enrollment.setParticipant(participant);
        enrollment.setEvent(event);
        enrollment.setDateTime(Instant.now());
        return enrollment;
    }

    public EnrollmentResponseDto toResponseDto(Enrollment enrollment) {
        if (enrollment == null) return null;

        Long spots = countRemainingSpots.calculate(enrollment.getEvent());

        return new EnrollmentResponseDto(
                enrollment.getId(),
                participantMapper.toResponseDto(enrollment.getParticipant()),
                eventMapper.toResponseDto(enrollment.getEvent(), spots),
                enrollment.getDateTime(),
                enrollment.getStatus()
        );
    }
}
