package com.richard.meetup.management.participant.controller;

import com.richard.meetup.management.participant.dto.ParticipantRequestDto;
import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.service.IParticipantService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import lombok.AllArgsConstructor;
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

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteParticipant(@RequestParam UUID id) {
        iParticipantService.deleteParticipant(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Participant deleted successfully"));
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateParticipant(@RequestBody ParticipantRequestDto dto, @RequestParam UUID id) {
        iParticipantService.updateParticipant(dto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Participant updated successfully"));
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponseDto>> fetchAllParticipants(){
        List<ParticipantResponseDto> list = iParticipantService.getAllParticipants();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
