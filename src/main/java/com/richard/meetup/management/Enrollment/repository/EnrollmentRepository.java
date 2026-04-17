package com.richard.meetup.management.Enrollment.repository;

import com.richard.meetup.management.Enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Long> countByEventId(UUID eventId);
}
