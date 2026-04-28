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
@RequestMapping(path = "/api/v1/event", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class EventController {

    private IEventService iEventService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createEvent(@RequestBody EventRequestDto dto) {
        iEventService.createEvent(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Event created successfully"));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<EventResponseDto> fetchEventDetails(@RequestParam UUID eventId) {
        EventResponseDto dto = iEventService.getEventById(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteEvent(@RequestParam UUID eventId) {
        iEventService.deleteEvent(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Event deleted successfully"));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateEvent(@RequestBody EventRequestDto dto, @RequestParam UUID eventId) {
        iEventService.updateEvent(dto, eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Event updated successfully"));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> list = iEventService.getAllEvents();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
