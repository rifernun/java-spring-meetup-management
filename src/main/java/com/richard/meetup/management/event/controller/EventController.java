package com.richard.meetup.management.event.controller;

import com.richard.meetup.management.event.dto.EventRequestDto;
import com.richard.meetup.management.event.dto.EventResponseDto;
import com.richard.meetup.management.event.service.IEventService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class EventController {

    private IEventService iEventService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto> createEvent(@RequestBody EventRequestDto dto) {
        iEventService.createEvent(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Event created successfully"));
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<EventResponseDto> fetchEventDetails(@PathVariable UUID eventId) {
        EventResponseDto dto = iEventService.getEventById(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable UUID eventId) {
        iEventService.deleteEvent(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Event deleted successfully"));
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDto> updateEvent(@RequestBody EventRequestDto dto, @PathVariable UUID eventId) {
        iEventService.updateEvent(dto, eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Event updated successfully"));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> list = iEventService.getAllEvents();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
