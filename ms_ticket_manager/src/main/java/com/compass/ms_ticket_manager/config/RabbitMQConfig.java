package com.compass.ms_ticket_manager.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue ticketQueue() {
        return new Queue("ticketQueue", true);
    }
}
