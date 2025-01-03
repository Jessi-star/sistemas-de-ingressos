package com.compass.ms_ticket_manager.service;

import com.compass.ms_ticket_manager.client.EventClient;
import com.compass.ms_ticket_manager.model.Event;
import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventClient eventClient;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        Ticket ticket = new Ticket();
        Event event = new Event();
        event.setId("1");
        ticket.setEvent(event);

        when(eventClient.getEventById("1")).thenReturn(event);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.createTicket(ticket);

        assertEquals(event, result.getEvent());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void testCreateTicketWithNullEvent() {
        Ticket ticket = new Ticket();
        ticket.setEvent(null);

        assertThrows(RuntimeException.class, () -> ticketService.createTicket(ticket));
    }

    @Test
    void testCreateTicketWithoutEventId() {
        Ticket ticket = new Ticket();

        assertThrows(RuntimeException.class, () -> ticketService.createTicket(ticket));
        verifyNoInteractions(ticketRepository);
        verifyNoInteractions(eventClient);
    }

    @Test
    void testGetTicketById() {
        Ticket ticket = new Ticket();
        when(ticketRepository.findById("1")).thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicketById("1");

        assertEquals(ticket, result);
        verify(ticketRepository, times(1)).findById("1");
    }

    @Test
    void testGetTicketByIdNotFound() {
        when(ticketRepository.findById("invalid_id")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ticketService.getTicketById("invalid_id"));
    }

    @Test
    void testGetAllTickets() {
        ticketService.getAllTickets();

        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTicket() {
        Ticket existingTicket = new Ticket();
        Ticket updatedTicket = new Ticket();
        when(ticketRepository.findById("1")).thenReturn(Optional.of(existingTicket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(updatedTicket);

        Ticket result = ticketService.updateTicket("1", updatedTicket);

        assertEquals(updatedTicket, result);
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }


    @Test
    void testDeleteNonExistentTicket() {

        when(ticketRepository.findById("1")).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.deleteTicket("1"));
        assertEquals("Ingresso não encontrado com o ID: 1", exception.getMessage());
        verify(ticketRepository, times(1)).findById("1");
        verify(ticketRepository, never()).delete(any(Ticket.class));
    }

    @Test
    void testDeleteTicketNotFound() {
        when(ticketRepository.findById("invalid_id")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ticketService.deleteTicket("invalid_id"));
    }

    @Test
    void testCreateTicketWithoutEvent() {

        Ticket ticket = new Ticket();

        assertThrows(RuntimeException.class, () -> ticketService.createTicket(ticket));
        verifyNoInteractions(ticketRepository);
        verifyNoInteractions(eventClient);
    }



}
