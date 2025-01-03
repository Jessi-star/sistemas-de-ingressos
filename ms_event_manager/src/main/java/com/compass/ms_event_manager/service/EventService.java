package com.compass.ms_event_manager.service;

import com.compass.ms_event_manager.client.ViaCepClient;
import com.compass.ms_event_manager.dto.Address;
import com.compass.ms_event_manager.model.Event;
import com.compass.ms_event_manager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EventService {
    @Autowired
    private  final EventRepository repository;

    @Autowired
    private final ViaCepClient viaCepClient;

    public EventService(EventRepository repository, ViaCepClient viaCepClient) {
        this.repository = repository;
        this.viaCepClient = viaCepClient;
    }

    public Event createEvent(Event event) {
        if (event.getCep() != null) {
            Address address = viaCepClient.getAddressByCep(event.getCep());
            if (address != null) {
                event.setLogradouro(address.getLogradouro());
                event.setBairro(address.getBairro());
                event.setCidade(address.getLocalidade());
                event.setUf(address.getUf());
            }
        }
        return repository.save(event);
    }

    public Event getEventById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event updateEvent(String id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setEventdate(updatedEvent.getEventdate());
        return repository.save(existingEvent);
    }

    public void deleteEvent(String id) {
        repository.deleteById(id);
    }
}
