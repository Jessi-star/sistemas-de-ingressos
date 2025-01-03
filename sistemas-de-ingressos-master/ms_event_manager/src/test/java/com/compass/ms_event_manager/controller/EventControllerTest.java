package com.compass.ms_event_manager.controller;

import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        Event mockEvent = new Event();
        mockEvent.setName("Event Name");

        when(eventService.createEvent(any(Event.class))).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.createEvent(mockEvent);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Event Name", response.getBody().getName());
    }

    @Test
    void testGetEventById() {
        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setName("Event Name");

        when(eventService.getEventById("1")).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.getEventById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Event Name", response.getBody().getName());
    }

    @Test
    void testGetAllEvents() {
        Event event1 = new Event();
        Event event2 = new Event();

        when(eventService.getAllEvents()).thenReturn(List.of(event1, event2));

        ResponseEntity<List<Event>> response = eventController.getAllEvents();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testUpdateEvent() {
        Event mockEvent = new Event();
        mockEvent.setId("1");
        mockEvent.setName("Updated Event");

        when(eventService.updateEvent(any(String.class), any(Event.class))).thenReturn(mockEvent);

        ResponseEntity<Event> response = eventController.updateEvent("1", mockEvent);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Event", response.getBody().getName());
    }

    @Test
    void testDeleteEvent() {
        doNothing().when(eventService).deleteEvent("1");


        ResponseEntity<Void> response = eventController.deleteEvent("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventService, times(1)).deleteEvent("1");
    }
}
