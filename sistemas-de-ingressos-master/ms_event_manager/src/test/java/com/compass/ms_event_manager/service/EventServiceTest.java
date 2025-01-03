package com.compass.ms_event_manager.service;

import com.compass.ms_event_manager.client.ViaCepClient;
import com.compass.ms_event_manager.dto.Address;
import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository repository;

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private EventService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        Event mockEvent = new Event();
        mockEvent.setCep("12345");

        Address address = new Address();
        address.setLogradouro("Rua Teste");
        address.setBairro("Bairro Teste");
        address.setLocalidade("Cidade Teste");
        address.setUf("UF");

        when(viaCepClient.getAddressByCep("12345")).thenReturn(address);
        when(repository.save(any(Event.class))).thenReturn(mockEvent);

        Event result = service.createEvent(mockEvent);

        assertEquals("Rua Teste", result.getLogradouro());
        assertEquals("Bairro Teste", result.getBairro());
        assertEquals("Cidade Teste", result.getCidade());
        assertEquals("UF", result.getUf());
        verify(viaCepClient, times(1)).getAddressByCep("12345");
        verify(repository, times(1)).save(any(Event.class));
    }

    @Test
    void testCreateEventWithoutCep() {
        Event mockEvent = new Event();
        mockEvent.setCep(null);

        when(repository.save(any(Event.class))).thenReturn(mockEvent);

        Event result = service.createEvent(mockEvent);

        assertNull(result.getLogradouro());
        assertNull(result.getBairro());
        assertNull(result.getCidade());
        assertNull(result.getUf());
        verify(repository, times(1)).save(any(Event.class));
        verifyNoInteractions(viaCepClient);
    }

    @Test
    void testCreateEventWithNullAddress() {
        Event mockEvent = new Event();
        mockEvent.setCep("12345");

        when(viaCepClient.getAddressByCep("12345")).thenReturn(null);
        when(repository.save(any(Event.class))).thenReturn(mockEvent);

        Event result = service.createEvent(mockEvent);

        assertNull(result.getLogradouro());
        assertNull(result.getBairro());
        assertNull(result.getCidade());
        assertNull(result.getUf());
        verify(viaCepClient, times(1)).getAddressByCep("12345");
        verify(repository, times(1)).save(any(Event.class));
    }

    @Test
    void testCreateEventWithInvalidCep() {
        Event mockEvent = new Event();
        mockEvent.setCep("invalid");

        when(viaCepClient.getAddressByCep("invalid")).thenReturn(null);
        when(repository.save(any(Event.class))).thenReturn(mockEvent);

        Event result = service.createEvent(mockEvent);

        assertNull(result.getLogradouro());
        assertNull(result.getBairro());
        assertNull(result.getCidade());
        assertNull(result.getUf());
        verify(viaCepClient, times(1)).getAddressByCep("invalid");
        verify(repository, times(1)).save(any(Event.class));
    }


    @Test
    void testUpdateNonExistentEvent() {
        Event updatedEvent = new Event();
        updatedEvent.setName("Updated Event");

        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateEvent("1", updatedEvent));
        verify(repository, times(1)).findById("1");
        verify(repository, never()).save(any(Event.class));
    }

    @Test
    void testUpdateEventPartialData() {
        Event existingEvent = new Event();
        existingEvent.setId("1");
        existingEvent.setName("Old Event");

        Event updatedEvent = new Event();
        updatedEvent.setName("Updated Event");

        when(repository.findById("1")).thenReturn(Optional.of(existingEvent));
        when(repository.save(any(Event.class))).thenReturn(updatedEvent);

        Event result = service.updateEvent("1", updatedEvent);

        assertEquals("Updated Event", result.getName());
        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).save(any(Event.class));
    }

    @Test
    void testUpdateEventWithSameData() {
        Event existingEvent = new Event();
        existingEvent.setId("1");
        existingEvent.setName("Same Event");

        Event updatedEvent = new Event();
        updatedEvent.setName("Same Event");

        when(repository.findById("1")).thenReturn(Optional.of(existingEvent));
        when(repository.save(any(Event.class))).thenReturn(updatedEvent);

        Event result = service.updateEvent("1", updatedEvent);

        assertEquals("Same Event", result.getName());
        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).save(any(Event.class));
    }



    @Test
    void testGetEventByIdNotFound() {
        when(repository.findById("1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getEventById("1"));
        verify(repository, times(1)).findById("1");
    }




}
