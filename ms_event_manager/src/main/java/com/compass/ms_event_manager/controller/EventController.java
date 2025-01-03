package com.compass.ms_event_manager.controller;

import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events/v1")
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private RestTemplate restTemplate;

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
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        String url = "http://localhost:8081/tickets/v1/check-tickets-by-event/" + id;
        Boolean hasTickets = restTemplate.getForObject(url, Boolean.class);

        if (Boolean.TRUE.equals(hasTickets)) {
            return ResponseEntity.status(409)
                    .body(Map.of("error", "O evento não pode ser deletado porque possui ingressos vendidos."));
        }

        service.deleteEvent(id);
        return ResponseEntity.ok(Map.of("message", "Evento deletado com sucesso!"));
    }


}

