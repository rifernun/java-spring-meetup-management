package com.richard.meetup.management.shared.utils;

import com.richard.meetup.management.enrollment.entity.enums.EnrollmentStatus;
import com.richard.meetup.management.enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.event.entity.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
