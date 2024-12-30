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

        Ticket responseTicket = new Ticket();
        responseTicket.setTicketId(createdTicket.getTicketId());
        responseTicket.setCpf(createdTicket.getCpf());
        responseTicket.setCustomerName(createdTicket.getCustomerName());
        responseTicket.setCustomerMail(createdTicket.getCustomerMail());
        responseTicket.setEvent(createdTicket.getEvent());
        responseTicket.setBRLtotalAmount(createdTicket.getBRLamount());
        responseTicket.setUSDtotalAmount(createdTicket.getUSDamount());
        responseTicket.setStatus("concluído");

        return ResponseEntity.ok(responseTicket);
    }

    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
        Ticket ticket = service.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/get-all-tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(service.getAllTickets());
    }

    @PutMapping("/update-ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = service.updateTicket(id, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/delete-ticket/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        service.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
