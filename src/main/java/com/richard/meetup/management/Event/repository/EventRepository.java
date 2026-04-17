package com.richard.meetup.management.Event.repository;

import com.richard.meetup.management.Event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findByTitle(String title);
}
