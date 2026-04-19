package com.richard.meetup.management.shared.utils;

import com.richard.meetup.management.Enrollment.entity.enums.EnrollmentStatus;
import com.richard.meetup.management.Enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.Event.entity.Event;
import com.richard.meetup.management.Event.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CountRemainingSpots {

    private final EnrollmentRepository enrollmentRepository;

    public Long calculate(Event event) {

        long activeEnrollments = enrollmentRepository.countByEventIdAndStatus(
                event.getId(),
                EnrollmentStatus.ACTIVE
        );

        return event.getMaxCapacity() - activeEnrollments;
    }
}
