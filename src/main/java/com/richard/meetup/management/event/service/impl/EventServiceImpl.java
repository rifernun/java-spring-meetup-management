package com.richard.meetup.management.event.service.impl;

import com.richard.meetup.management.enrollment.repository.EnrollmentRepository;
import com.richard.meetup.management.event.dto.EventRequestDto;
import com.richard.meetup.management.event.dto.EventResponseDto;
import com.richard.meetup.management.event.entity.Event;
import com.richard.meetup.management.event.exception.EventAlreadyExists;
import com.richard.meetup.management.event.exception.EventNotFound;
import com.richard.meetup.management.event.mapper.EventMapper;
import com.richard.meetup.management.event.repository.EventRepository;
import com.richard.meetup.management.event.service.IEventService;
import com.richard.meetup.management.shared.utils.CountRemainingSpots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements IEventService {

    private final EventRepository repository;
    private final EventMapper eventMapper;
    private final CountRemainingSpots spots;

    public EventServiceImpl(EventRepository repository, EventMapper eventMapper, CountRemainingSpots spots){
        this.repository = repository;
        this.eventMapper = eventMapper;
        this.spots = spots;
    }

    @Override
    public void createEvent(EventRequestDto eventRequestDto) {
        Event event = eventMapper.toEntity(eventRequestDto);
        Optional<Event> optionalEvent = repository.findByTitle(event.getTitle());
        if(optionalEvent.isPresent()){
            throw new EventAlreadyExists("Event with the same title already exists");
        } else {
            event.setCreatedAt(Instant.now());
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            event.setCreatedBy(email);
            repository.save(event);
        }
    }

    @Override
    public void deleteEvent(UUID id) {
        if(repository.findById(id).isEmpty()){
            throw new EventNotFound("Event not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void updateEvent(EventRequestDto eventRequestDto, UUID id) {
        if(repository.findById(id).isEmpty()){
            throw new EventNotFound("Event not found with id: " + id);
        }
        Event event = repository.getReferenceById(id);
        event.setTitle(eventRequestDto.title());
        event.setDescription(eventRequestDto.description());
        event.setDateTime(eventRequestDto.dateTime());
        event.setLocation(eventRequestDto.location());
        event.setMaxCapacity(eventRequestDto.maxCapacity());
        event.setUpdatedAt(Instant.now());
        repository.save(event);
    }

    @Override
    public EventResponseDto getEventById(UUID id) {
        EventResponseDto dto = repository.findById(id)
                .map(event -> {
                    Long remainingSpots = spots.calculate(event);
                    return eventMapper.toResponseDto(event, remainingSpots);
                })
                .orElseThrow(() -> new EventNotFound("Event not found with id: " + id));
        return dto;
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<EventResponseDto> list = repository.findAll().stream().map(event -> {
            Long remainingSpots = spots.calculate(event);
            return eventMapper.toResponseDto(event, remainingSpots);
        }).toList();
        return list;
    }

}