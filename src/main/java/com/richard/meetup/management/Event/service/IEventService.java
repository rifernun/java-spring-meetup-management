package com.richard.meetup.management.Event.service;

import com.richard.meetup.management.Event.dto.EventRequestDto;
import com.richard.meetup.management.Event.dto.EventResponseDto;

import java.util.List;
import java.util.UUID;

public interface IEventService {
    void createEvent(EventRequestDto eventRequestDto);
    void deleteEvent();
    void updateEvent(EventResponseDto eventResponseDto);
    EventResponseDto getEventById(UUID id);
    List<EventResponseDto> getAllEvents();
}
