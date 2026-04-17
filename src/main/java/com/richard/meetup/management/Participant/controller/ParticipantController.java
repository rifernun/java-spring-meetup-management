package com.richard.meetup.management.Participant.controller;

import com.richard.meetup.management.Participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.Participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.Participant.service.IParticipantService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/participant", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ParticipantController {

    private IParticipantService iParticipantService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createParticipant(@RequestBody ParticipantRequestDto dto) {
         iParticipantService.createParticipant(dto);
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(new ResponseDto("201", "Participant created successfully"));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<ParticipantResponseDto> fetchParticipantDetails(@RequestParam UUID id) {
        ParticipantResponseDto dto = iParticipantService.getParticipantById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponseDto>> fetchAllParticipants(){
        List<ParticipantResponseDto> list = iParticipantService.getAllParticipants();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
