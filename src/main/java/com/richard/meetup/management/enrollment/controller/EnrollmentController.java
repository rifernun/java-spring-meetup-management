package com.richard.meetup.management.enrollment.controller;

import com.richard.meetup.management.enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.enrollment.dto.EnrollmentResponseDto;
import com.richard.meetup.management.enrollment.service.IEnrollmentService;
import com.richard.meetup.management.shared.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Enrollments", description = "Endpoints for managing event enrollments")
public class EnrollmentController {

    private IEnrollmentService iEnrollmentService;

    @PostMapping
    @Operation(
            summary = "Create an enrollment",
            description = "Register a participant for an event."
    )
    @ApiResponse(responseCode = "201", description = "Enrollment created successfully")
    public ResponseEntity<ResponseDto> createEnrollment(@RequestBody EnrollmentRequestDto dto) {
        iEnrollmentService.createEnrollment(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Enrollment created successfully"));
    }

    @PatchMapping(path = "/{id}/cancel")
    @Operation(
            summary = "Cancel an enrollment",
            description = "Cancel an existing enrollment by its ID."
    )
    @ApiResponse(responseCode = "200", description = "Enrollment cancelled successfully")
    public ResponseEntity<ResponseDto> cancelEnrollment(@PathVariable UUID id) {
        iEnrollmentService.cancelEnrollment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Enrollment cancelled successfully"));
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Get enrollment details",
            description = "Retrieve details of a specific enrollment."
    )
    @ApiResponse(responseCode = "200", description = "Enrollment details retrieved successfully")
    public ResponseEntity<EnrollmentResponseDto> fetchEnrollmentDetails(@PathVariable UUID id) {
        EnrollmentResponseDto responseDto = iEnrollmentService.getEnrollmentById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    @GetMapping
    @Operation(
            summary = "Get all enrollments",
            description = "Retrieve a list of all enrollments."
    )
    @ApiResponse(responseCode = "200", description = "Enrollments list retrieved successfully")
    public ResponseEntity<List<EnrollmentResponseDto>> fetchAllEnrollments() {
        List<EnrollmentResponseDto> list = iEnrollmentService.getAllEnrollments();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
