package com.richard.meetup.management.Enrollment.repository;

import com.richard.meetup.management.Enrollment.entity.Enrollment;
import com.richard.meetup.management.Enrollment.entity.enums.EnrollmentStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Enrollment> findByParticipantIdAndEventId(@NotNull(message = "Participant ID is required") UUID uuid, @NotNull(message = "Event ID is required") UUID uuid1);
    long countByEventIdAndStatus(UUID eventId, EnrollmentStatus status);
}
