package com.richard.meetup.management.Event.service.impl;

import com.richard.meetup.management.Enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.Event.dto.EventRequestDto;
import com.richard.meetup.management.Event.dto.EventResponseDto;
import com.richard.meetup.management.Event.entity.Event;
import com.richard.meetup.management.Event.mapper.EventMapper;
import com.richard.meetup.management.Event.repository.EventRepository;
import com.richard.meetup.management.Event.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository repository;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public void createEvent(EventRequestDto eventRequestDto) {
        Event event = EventMapper.toEntity(eventRequestDto);
        Optional<Event> optionalEvent = repository.findByTitle(event.getTitle());
        if(optionalEvent.isPresent()){
            throw new RuntimeException("Event with the same title already exists");
        } else {
            event.setCreatedAt(Instant.now());
            event.setCreatedBy("system"); // In a real application, this should be the authenticated user
            repository.save(event);
        }
    }

    @Override
    public void deleteEvent() {

    }

    @Override
    public void updateEvent(EventResponseDto eventResponseDto) {

    }

    @Override
    public EventResponseDto getEventById(UUID id) {
        EventResponseDto dto = repository.findById(id)
                .map(event -> {
                    Long remainingSpots = event.getMaxCapacity();
                    return EventMapper.toResponseDto(event, remainingSpots);
                })
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return dto;
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<EventResponseDto> list = repository.findAll().stream().map(event -> {
            Long remainingSpots = remainingSpots(event);
            return EventMapper.toResponseDto(event, remainingSpots);
        }).toList();
        return list;
    }

    // TODO - verify later if the logic is correct
    private Long remainingSpots(Event event) {
        Optional<Long> countedEvents = enrollmentRepository.countByEventId(event.getId());
        return event.getMaxCapacity() - countedEvents.orElse(0L);
    }
}
