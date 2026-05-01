package com.richard.meetup.management.participant.controller;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.service.IParticipantService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/participants", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Tag(name = "Participants", description = "Endpoints for managing meetup participants")
public class ParticipantController {

    private IParticipantService iParticipantService;

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Get participant details",
            description = "Retrieve details of a specific participant by their ID."
    )
    @ApiResponse(responseCode = "200", description = "Participant details retrieved successfully")
    public ResponseEntity<ParticipantResponseDto> fetchParticipantDetails(@PathVariable UUID id) {
        ParticipantResponseDto dto = iParticipantService.getParticipantById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Delete a participant",
            description = "Remove a participant from the system."
    )
    @ApiResponse(responseCode = "200", description = "Participant deleted successfully")
    public ResponseEntity<ResponseDto> deleteParticipant(@PathVariable UUID id) {
        iParticipantService.deleteParticipant(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Participant deleted successfully"));
    }

    @PutMapping(path = "/{id}")
    @Operation(
            summary = "Update a participant",
            description = "Modify details of an existing participant by ID."
    )
    @ApiResponse(responseCode = "200", description = "Participant updated successfully")
    public ResponseEntity<ResponseDto> updateParticipant(@RequestBody ParticipantRequestDto dto, @PathVariable UUID id) {
        iParticipantService.updateParticipant(dto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Participant updated successfully"));
    }

    @PutMapping("/me")
    @Operation(
            summary = "Update current participant",
            description = "Modify details of the currently authenticated participant."
    )
    @ApiResponse(responseCode = "200", description = "Profile updated successfully")
    public ResponseEntity<ResponseDto> updateMe(@RequestBody ParticipantRequestDto dto) {
        iParticipantService.updateParticipant(dto, null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Participant updated successfully"));
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get current participant details",
            description = "Retrieve details of the currently authenticated participant."
    )
    @ApiResponse(responseCode = "200", description = "Profile details retrieved successfully")
    public ResponseEntity<ParticipantResponseDto> fetchMyDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ParticipantResponseDto dto = iParticipantService.getParticipantByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping
    @Operation(
            summary = "Get all participants",
            description = "Retrieve a list of all registered participants."
    )
    @ApiResponse(responseCode = "200", description = "Participants list retrieved successfully")
    public ResponseEntity<List<ParticipantResponseDto>> fetchAllParticipants(){
        List<ParticipantResponseDto> list = iParticipantService.getAllParticipants();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
