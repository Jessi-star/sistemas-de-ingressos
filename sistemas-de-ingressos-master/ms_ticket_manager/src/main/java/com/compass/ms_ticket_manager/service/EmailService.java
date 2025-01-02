package com.compass.ms_ticket_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Email enviado com sucesso para: " + to);
    }

    public void testEmail() {
        sendEmail("seu-email@gmail.com", "Teste de Email", "Este é um email de teste enviado pelo Spring Boot.");
    }
}
