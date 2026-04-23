package com.richard.meetup.management.enrollment.mapper;

import com.richard.meetup.management.enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.enrollment.entity.Enrollment;
import com.richard.meetup.management.event.entity.Event;
import com.richard.meetup.management.event.mapper.EventMapper;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.participant.mapper.ParticipantMapper;

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
