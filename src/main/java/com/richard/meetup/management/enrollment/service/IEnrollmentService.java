package com.richard.meetup.management.enrollment.service;


import com.richard.meetup.management.enrollment.dto.EnrollmentRequestDto;
import com.richard.meetup.management.enrollment.dto.EnrollmentResponseDto;

import java.util.List;
import java.util.UUID;

public interface IEnrollmentService {
    void createEnrollment(EnrollmentRequestDto enrollmentRequestDto);
    void cancelEnrollment(UUID id);
    EnrollmentResponseDto getEnrollmentById(UUID id);
    List<EnrollmentResponseDto> getAllEnrollments();

}
