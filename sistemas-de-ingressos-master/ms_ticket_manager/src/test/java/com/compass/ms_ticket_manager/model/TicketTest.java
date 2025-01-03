package com.compass.ms_ticket_manager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testGettersAndSetters() {
        Ticket ticket = new Ticket();
        Event event = new Event();
        event.setId("1");

        ticket.setTicketId("123");
        ticket.setCpf("12345678900");
        ticket.setCustomerName("John Doe");
        ticket.setCustomerMail("johndoe@example.com");
        ticket.setEvent(event);
        ticket.setBrlTotalAmount("150.00");
        ticket.setUsdTotalAmount("30.00");
        ticket.setStatus("CONFIRMED");

        assertEquals("123", ticket.getTicketId());
        assertEquals("12345678900", ticket.getCpf());
        assertEquals("John Doe", ticket.getCustomerName());
        assertEquals("johndoe@example.com", ticket.getCustomerMail());
        assertEquals(event, ticket.getEvent());
        assertEquals("150.00", ticket.getBrlTotalAmount());
        assertEquals("30.00", ticket.getUsdTotalAmount());
        assertEquals("CONFIRMED", ticket.getStatus());
    }

    @Test
    void testDefaultConstructor() {
        Ticket ticket = new Ticket();
        assertNotNull(ticket);
    }

    @Test
    void testNullFields() {
        Ticket ticket = new Ticket();
        ticket.setTicketId(null);
        ticket.setCpf(null);
        ticket.setCustomerName(null);
        ticket.setCustomerMail(null);
        ticket.setEvent(null);
        ticket.setBrlTotalAmount(null);
        ticket.setUsdTotalAmount(null);
        ticket.setStatus(null);

        assertNull(ticket.getTicketId());
        assertNull(ticket.getCpf());
        assertNull(ticket.getCustomerName());
        assertNull(ticket.getCustomerMail());
        assertNull(ticket.getEvent());
        assertNull(ticket.getBrlTotalAmount());
        assertNull(ticket.getUsdTotalAmount());
        assertNull(ticket.getStatus());
    }

    @Test
    void testEventAssociation() {
        Event event = new Event();
        event.setId("1");
        event.setName("Concert");

        Ticket ticket = new Ticket();
        ticket.setEvent(event);

        assertNotNull(ticket.getEvent());
        assertEquals("1", ticket.getEvent().getId());
        assertEquals("Concert", ticket.getEvent().getName());
    }

    @Test
    void testStatusValues() {
        Ticket ticket = new Ticket();
        ticket.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", ticket.getStatus());

        ticket.setStatus("CANCELLED");
        assertEquals("CANCELLED", ticket.getStatus());
    }
}
