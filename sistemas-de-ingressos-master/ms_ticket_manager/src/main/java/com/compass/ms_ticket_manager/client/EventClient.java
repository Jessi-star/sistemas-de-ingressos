package com.compass.ms_ticket_manager.client;

import com.compass.ms_ticket_manager.dto.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FeignClient(name = "ms-event-manager", url = "http://localhost:8080/events/v1")
public interface EventClient {
    Logger logger = LoggerFactory.getLogger(EventClient.class);

    @GetMapping("/get-event/{id}")
    Event getEventById(@PathVariable("id") String id);
}



//@FeignClient(name = "ms-event-manager", url = "http://<ip-ms-event-manager>:8080/events/v1")
//public interface EventClient {
    //@GetMapping("/get-event/{id}")
    //Event getEventById(@PathVariable("id") String id);
//}

//Substituir <ip-ms-event-manager> pelo IP do EC2 onde o serviço está rodando.
