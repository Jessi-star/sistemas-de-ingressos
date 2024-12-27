package com.compass.ms_event_manager.service;


import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public Event getEventById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event updateEvent(String id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDate(updatedEvent.getDate());
        return repository.save(existingEvent);
    }

    public void deleteEvent(String id) {
        repository.deleteById(id);
    }
}
