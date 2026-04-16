package com.richard.meetup.management.Event.mapper;

import com.richard.meetup.management.Event.dto.EventRequestDto;
import com.richard.meetup.management.Event.dto.EventResponseDto;
import com.richard.meetup.management.Event.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public static Event toEntity(EventRequestDto dto) {
        if (dto == null) return null;

        Event event = new Event();
        event.setTitle(dto.title());
        event.setDescription(dto.description());
        event.setDateTime(dto.dateTime());
        event.setLocation(dto.location());
        event.setMaxCapacity(dto.maxCapacity());
        return event;
    }

    public static EventResponseDto toResponseDto(Event event, Long remainingSpots) {
        if (event == null) return null;

        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDateTime(),
                event.getLocation(),
                event.getMaxCapacity(),
                remainingSpots
        );
    }
}
