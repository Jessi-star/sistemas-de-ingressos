package com.compass.ms_ticket_manager.client;

import com.compass.ms_ticket_manager.model.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-client", url = "http://ms_event_manager:8080")
public interface EventClient {

    @GetMapping("/events/v1/get-event/{id}")
    Event getEventById(@PathVariable("id") String id);
}

