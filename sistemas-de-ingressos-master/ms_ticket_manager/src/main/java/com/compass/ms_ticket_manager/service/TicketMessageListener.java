package com.compass.ms_ticket_manager.service;

import org.springframework.stereotype.Service;

@Service
public class TicketMessageListener {

    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        sendEmail(message);
    }

    private void sendEmail(String ticketDetails) {

        String emailMessage = "Obrigado por sua compra! Aqui estão os detalhes do seu ingresso:\n" + ticketDetails;
        System.out.println("Email enviado: \n" + emailMessage);
    }
}
