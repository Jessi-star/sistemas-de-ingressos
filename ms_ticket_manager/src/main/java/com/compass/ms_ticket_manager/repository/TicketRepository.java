package com.compass.ms_ticket_manager.repository;

import com.compass.ms_ticket_manager.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    boolean existsByEventId(String eventId);
}