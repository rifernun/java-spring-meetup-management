package com.richard.meetup.management.Enrollment.mapper;

import com.richard.meetup.management.Enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.Enrollment.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponseDto toResponseDto(Enrollment enrollment) {
        if (enrollment == null) return null;

        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getParticipant() != null ? enrollment.getParticipant().getId() : null,
                enrollment.getEvent() != null ? enrollment.getEvent().getId() : null,
                enrollment.getDateTime()
        );
    }
}
