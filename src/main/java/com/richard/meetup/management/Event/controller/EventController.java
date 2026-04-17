package com.richard.meetup.management.Event.controller;

import com.richard.meetup.management.Event.dto.EventRequestDto;
import com.richard.meetup.management.Event.dto.EventResponseDto;
import com.richard.meetup.management.Event.service.IEventService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/events", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @GetMapping(path = "/{eventId}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable("eventId") UUID eventId) {
        EventResponseDto dto = iEventService.getEventById(eventId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> list = iEventService.getAllEvents();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
