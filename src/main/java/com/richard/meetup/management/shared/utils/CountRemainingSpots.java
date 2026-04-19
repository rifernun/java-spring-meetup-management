package com.richard.meetup.management.shared.utils;

import com.richard.meetup.management.Enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.Event.entity.Event;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CountRemainingSpots {

    private final EnrollmentRepository enrollmentRepository;

    public Long calculate(Event event) {
        Optional<Long> countedEvents = enrollmentRepository.countByEventId(event.getId());
        return event.getMaxCapacity() - countedEvents.orElse(0L);
    }
}
