package com.compass.ms_ticket_manager.service;

import com.compass.ms_ticket_manager.client.EventClient;
import com.compass.ms_ticket_manager.dto.Event;
import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.repository.TicketRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private EventClient eventClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Ticket createTicket(Ticket ticket) {
        Event event = eventClient.getEventById(ticket.getEventId());
        if (event == null) {
            throw new RuntimeException("Evento não encontrado: " + ticket.getEventId());
        }

        if (repository.existsByEventId(ticket.getEventId())) {
            throw new RuntimeException("Já existe um ticket vinculado ao evento: " + ticket.getEventId());
        }

        ticket.setPurchaseDate(LocalDate.now().toString());

        Ticket createdTicket = repository.save(ticket);

        rabbitTemplate.convertAndSend("ticket.exchange", "ticket.routing.key", createdTicket);

        return createdTicket;
    }

    public Ticket getTicketById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
    }

    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    public Ticket updateTicket(String id, Ticket updatedTicket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setBuyerName(updatedTicket.getBuyerName());
        existingTicket.setPrice(updatedTicket.getPrice());
        return repository.save(existingTicket);
    }

    public void deleteTicket(String id) {
        repository.deleteById(id);
    }
}
