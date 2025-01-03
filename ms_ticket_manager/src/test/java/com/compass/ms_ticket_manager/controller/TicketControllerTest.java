package com.compass.ms_ticket_manager.controller;

import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {

        Ticket ticket = new Ticket();
        when(ticketService.createTicket(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.createTicket(ticket);

        assertEquals(ticket, response.getBody());
        verify(ticketService, times(1)).createTicket(any(Ticket.class));
    }

    @Test
    void testCreateTicketWithInvalidData() {
        Ticket ticket = new Ticket();
        ticket.setEvent(null);

        when(ticketService.createTicket(ticket)).thenThrow(new RuntimeException("Event ID is required"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketController.createTicket(ticket);
        });

        assertEquals("Event ID is required", exception.getMessage());
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        when(ticketService.getTicketById("1")).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.getTicketById("1");

        assertEquals(ticket, response.getBody());
        verify(ticketService, times(1)).getTicketById("1");
    }

    @Test
    void testGetAllTickets() {
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(ticketService.getAllTickets()).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        assertEquals(tickets, response.getBody());
        verify(ticketService, times(1)).getAllTickets();
    }

    @Test
    void testGetTicketByIdNotFound() {
        when(ticketService.getTicketById("invalid_id")).thenThrow(new RuntimeException("Ticket not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ticketController.getTicketById("invalid_id");
        });

        assertEquals("Ticket not found", exception.getMessage());
    }

    @Test
    void testUpdateTicket() {
        Ticket ticket = new Ticket();
        when(ticketService.updateTicket(eq("1"), any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.updateTicket("1", ticket);

        assertEquals(ticket, response.getBody());
        verify(ticketService, times(1)).updateTicket(eq("1"), any(Ticket.class));
    }

    @Test
    void testDeleteTicket() {
        ResponseEntity<Void> response = ticketController.deleteTicket("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(ticketService, times(1)).deleteTicket("1");
    }
}
