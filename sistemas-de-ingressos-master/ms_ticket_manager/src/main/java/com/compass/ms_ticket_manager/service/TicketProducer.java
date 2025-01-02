package com.compass.ms_ticket_manager.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTicketMessage(String message) {
        rabbitTemplate.convertAndSend("ticketQueue", message);
    }
}
