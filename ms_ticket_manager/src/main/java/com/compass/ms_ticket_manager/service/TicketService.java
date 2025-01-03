package com.compass.ms_ticket_manager.service;

import com.compass.ms_ticket_manager.client.EventClient;
import com.compass.ms_ticket_manager.model.Event;
import com.compass.ms_ticket_manager.model.Ticket;
import com.compass.ms_ticket_manager.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventClient eventClient;

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getEvent() == null || ticket.getEvent().getId() == null || ticket.getEvent().getId().isEmpty()) {
            throw new RuntimeException("O ID do evento é obrigatório para criar um ticket.");
        }

        String eventId = ticket.getEvent().getId();
        Event event = eventClient.getEventById(eventId);
        ticket.setEvent(event);
        ticket.setStatus("concluído");

        return ticketRepository.save(ticket);
    }


    public Ticket getTicketById(String id) {
        return ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ingresso não encontrado com o ID: " + id)
        );
    }

    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(String id, Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ingresso não encontrado com o ID: " + id)
        );

        existingTicket.setCustomerName(updatedTicket.getCustomerName());
        existingTicket.setCustomerMail(updatedTicket.getCustomerMail());
        existingTicket.setBrlTotalAmount(updatedTicket.getBrlTotalAmount());
        existingTicket.setUsdTotalAmount(updatedTicket.getUsdTotalAmount());

        return ticketRepository.save(existingTicket);
    }

    public void deleteTicket(String id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ingresso não encontrado com o ID: " + id)
        );

        ticketRepository.delete(ticket);
    }

    public boolean checkTicketsByEvent(String eventId) {
        return ticketRepository.existsByEventId(eventId);
    }
}
