package com.compass.ms_ticket_manager.service;

import com.compass.ms_ticket_manager.client.EventClient;
import com.compass.ms_ticket_manager.dto.Event;
import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;

@Service
public class TicketService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventClient eventClient;

    @Autowired
    private RabbitMQService rabbitMQService;

    public Ticket createTicket(Ticket ticket) {
        ticket.setTicketId(UUID.randomUUID().toString());

        Event event = eventClient.getEventById(ticket.getEventId());
        if (event == null) {
            throw new RuntimeException("Evento não encontrado para ID: " + ticket.getEventId());
        }

        ticket.setEventName(event.getName());
        ticket.setLocation(event.getLocation());
        ticket.setDate(event.getDate());
        ticket.setBRLamount(ticket.getBRLamount());
        ticket.setUSDamount(ticket.getUSDamount());
        ticket.setStatus("concluído");
        Ticket savedTicket = ticketRepository.save(ticket);

        Ticket outputTicket = new Ticket();
        outputTicket.setTicketId(savedTicket.getTicketId());
        outputTicket.setCpf(savedTicket.getCpf());
        outputTicket.setCustomerName(savedTicket.getCustomerName());
        outputTicket.setCustomerMail(savedTicket.getCustomerMail());
        outputTicket.setEvent(savedTicket.getEvent());
        outputTicket.setBRLtotalAmount(savedTicket.getBRLamount());
        outputTicket.setUSDtotalAmount(savedTicket.getUSDamount());
        outputTicket.setStatus(savedTicket.getStatus());

        return outputTicket;
    }

    public Ticket getTicketById(String id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(String id, Ticket updatedTicket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setCustomerName(updatedTicket.getCustomerName());
        existingTicket.setBRLamount(updatedTicket.getBRLamount());
        existingTicket.setUSDamount(updatedTicket.getUSDamount());

        return ticketRepository.save(existingTicket);
    }

    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }
}
