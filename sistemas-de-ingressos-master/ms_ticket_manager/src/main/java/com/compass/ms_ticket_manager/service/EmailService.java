package com.compass.ms_ticket_manager.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String ticketDetails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Detalhes do seu Ingresso");
        mailMessage.setText("Obrigado por sua compra! Aqui estão os detalhes do seu ingresso:\n" + ticketDetails);

        mailSender.send(mailMessage);
        System.out.println("Email enviado para: " + to);
    }
}
