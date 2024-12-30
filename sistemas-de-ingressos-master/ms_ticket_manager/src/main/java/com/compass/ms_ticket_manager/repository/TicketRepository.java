package com.compass.ms_ticket_manager.repository;

import com.compass.ms_ticket_manager.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    boolean existsByEventId(String eventId);
}

