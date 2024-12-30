package com.compass.ms_ticket_manager.controller;

import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets/v1")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = service.createTicket(ticket);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
        return ResponseEntity.ok(service.getTicketById(id));
    }

    @GetMapping("/get-all-tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(service.getAllTickets());
    }

    @PutMapping("/update-ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(service.updateTicket(id, ticket));
    }

    @DeleteMapping("/delete-ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
