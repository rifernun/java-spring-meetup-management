package com.richard.meetup.management.enrollment.controller;

import com.richard.meetup.management.enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.enrollment.service.IEnrollmentService;
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
@RequestMapping(path = "/api/v1/enrollments", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EnrollmentController {

    private IEnrollmentService iEnrollmentService;

    @PostMapping
    public ResponseEntity<ResponseDto> createEnrollment(@RequestBody EnrollmentRequestDto dto) {
        iEnrollmentService.createEnrollment(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Enrollment created successfully"));
    }

    @PatchMapping(path = "/{id}/cancel")
    public ResponseEntity<ResponseDto> cancelEnrollment(@PathVariable UUID id) {
        iEnrollmentService.cancelEnrollment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Enrollment cancelled successfully"));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EnrollmentResponseDto> fetchEnrollmentDetails(@PathVariable UUID id) {
        EnrollmentResponseDto responseDto = iEnrollmentService.getEnrollmentById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
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
