package com.compass.ms_ticket_manager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class TicketConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private TicketConsumer ticketConsumer;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testReceiveMessageWithValidData() throws Exception {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("email", "test@example.com");
        messageData.put("subject", "Test Subject");
        messageData.put("body", "Test Body");

        Map<String, String> ticketDetails = new HashMap<>();
        ticketDetails.put("ticketNumber", "123");
        ticketDetails.put("event", "Test Event");
        ticketDetails.put("eventDate", "2025-01-01");
        ticketDetails.put("eventTime", "20:00");
        ticketDetails.put("venue", "Test Venue");
        ticketDetails.put("price", "100.00");

        messageData.put("ticketDetails", ticketDetails);

        String message = objectMapper.writeValueAsString(messageData);

        ticketConsumer.receiveMessage(message);

        verify(emailService, times(1)).sendEmail(
                eq("test@example.com"),
                eq("Test Subject"),
                contains("Detalhes do Ticket")
        );
    }

    @Test
    void testReceiveMessageWithInvalidData() {
        String message = "invalid_message";

        ticketConsumer.receiveMessage(message);

        verifyNoInteractions(emailService);
    }

    @Test
    void testReceiveMessageWithoutTicketDetails() throws Exception {
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("email", "test@example.com");
        messageData.put("subject", "Test Subject");
        messageData.put("body", "Test Body");

        String message = objectMapper.writeValueAsString(messageData);

        ticketConsumer.receiveMessage(message);

        verify(emailService, times(1)).sendEmail(
                eq("test@example.com"),
                eq("Test Subject"),
                eq("Test Body")
        );
    }

    @Test
    void testReceiveMessageWithMalformedJson() {
        String invalidMessage = "{invalid_json";

        ticketConsumer.receiveMessage(invalidMessage);

        verifyNoInteractions(emailService);
    }


}
