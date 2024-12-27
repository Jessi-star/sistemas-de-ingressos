package com.compass.ms_event_manager.controller;

import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/v1")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        return ResponseEntity.ok(service.createEvent(event));
    }

    @GetMapping("/get-event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(service.getEventById(id));
    }

    @GetMapping("/get-all-events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(service.getAllEvents());
    }


    @PutMapping("/update-event/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @Valid @RequestBody Event event) {
        return ResponseEntity.ok(service.updateEvent(id, event));
    }

    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}

