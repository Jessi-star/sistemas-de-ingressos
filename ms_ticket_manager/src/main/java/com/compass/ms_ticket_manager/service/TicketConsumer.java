package com.compass.ms_ticket_manager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TicketConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "ticketQueue")
    public void receiveMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> messageData = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});

            String userEmail = (String) messageData.get("email");
            String subject = (String) messageData.get("subject");
            String body = (String) messageData.get("body");

            Map<String, String> ticketDetails = (Map<String, String>) messageData.get("ticketDetails");
            if (ticketDetails != null) {
                body += "\n\nDetalhes do Ticket:\n"
                        + "Número: " + ticketDetails.get("ticketNumber") + "\n"
                        + "Evento: " + ticketDetails.get("event") + "\n"
                        + "Data: " + ticketDetails.get("eventDate") + "\n"
                        + "Horário: " + ticketDetails.get("eventTime") + "\n"
                        + "Local: " + ticketDetails.get("venue") + "\n"
                        + "Preço: R$" + ticketDetails.get("price");
            }


            emailService.sendEmail(userEmail, subject, body);
            System.out.println("Email enviado para: " + userEmail);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}
