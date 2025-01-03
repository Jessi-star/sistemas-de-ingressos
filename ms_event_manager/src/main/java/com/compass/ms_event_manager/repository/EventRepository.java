package com.compass.ms_event_manager.repository;

import com.compass.ms_event_manager.model.Event;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    Optional<Event> findByName(String name);
}
