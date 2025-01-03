package com.compass.ms_ticket_manager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testGettersAndSetters() {
        Event event = new Event();

        event.setId("1");
        event.setName("Concert");
        event.setEventDate("2025-01-01");
        event.setLogradouro("Rua Teste");
        event.setBairro("Bairro Teste");
        event.setCidade("Cidade Teste");
        event.setUf("CE");

        assertEquals("1", event.getId());
        assertEquals("Concert", event.getName());
        assertEquals("2025-01-01", event.getEventdate());
        assertEquals("Rua Teste", event.getLogradouro());
        assertEquals("Bairro Teste", event.getBairro());
        assertEquals("Cidade Teste", event.getCidade());
        assertEquals("CE", event.getUf());
    }

    @Test
    void testDefaultConstructor() {
        Event event = new Event();
        assertNotNull(event);
    }

    @Test
    void testNullFields() {
        Event event = new Event();
        event.setId(null);
        event.setName(null);
        event.setEventDate(null);
        event.setLogradouro(null);
        event.setBairro(null);
        event.setCidade(null);
        event.setUf(null);

        assertNull(event.getId());
        assertNull(event.getName());
        assertNull(event.getEventdate());
        assertNull(event.getLogradouro());
        assertNull(event.getBairro());
        assertNull(event.getCidade());
        assertNull(event.getUf());
    }

    @Test
    void testEquality() {
        Event event1 = new Event();
        event1.setId("1");
        event1.setName("Event 1");

        Event event2 = new Event();
        event2.setId("1");
        event2.setName("Event 1");

        assertEquals(event1.getId(), event2.getId());
        assertEquals(event1.getName(), event2.getName());
    }
}
