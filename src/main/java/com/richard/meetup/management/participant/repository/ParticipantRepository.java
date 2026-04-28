package com.richard.meetup.management.participant.repository;

import com.richard.meetup.management.participant.dto.ParticipantResponseDto;
import com.richard.meetup.management.participant.entity.Participant;
import com.richard.meetup.management.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    Optional<Participant> findByUser(User user);
    Optional<Participant> findByUserEmail(String email);

}
