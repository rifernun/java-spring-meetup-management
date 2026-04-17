package com.richard.meetup.management.Enrollment.controller;

import com.richard.meetup.management.Enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.Enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.Enrollment.service.IEnrollmentService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/enrollment", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class EnrollmentController {

    private IEnrollmentService iEnrollmentService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createEnrollment(@RequestBody EnrollmentRequestDto dto) {
        iEnrollmentService.createEnrollment(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Enrollment created successfully"));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<EnrollmentResponseDto> fetchEnrollmentDetails(@RequestParam UUID id) {
        EnrollmentResponseDto responseDto = iEnrollmentService.getEnrollmentById(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> fetchAllEnrollments() {
        List<EnrollmentResponseDto> list = iEnrollmentService.getAllEnrollments();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
