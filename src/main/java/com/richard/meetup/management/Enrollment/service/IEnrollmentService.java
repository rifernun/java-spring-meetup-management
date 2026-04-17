package com.richard.meetup.management.Enrollment.service;


import com.richard.meetup.management.Enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.Enrollment.dto.EnrollmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface IEnrollmentService {
    void createEnrollment(EnrollmentRequestDto enrollmentRequestDto);
    void deleteEnrollment(UUID id);
    void updateEnrollment(EnrollmentResponseDto enrollmentResponseDto);
    EnrollmentResponseDto getEnrollmentById(UUID id);
    List<EnrollmentResponseDto> getAllEnrollments();
}
