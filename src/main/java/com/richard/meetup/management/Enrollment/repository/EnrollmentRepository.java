package com.richard.meetup.management.Enrollment.repository;

import com.richard.meetup.management.Enrollment.entity.Enrollment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    Optional<Long> countByEventId(UUID eventId);

    boolean existsByParticipantIdAndEventId(@NotNull(message = "Participant ID is required") UUID uuid, @NotNull(message = "Event ID is required") UUID uuid1);
}
