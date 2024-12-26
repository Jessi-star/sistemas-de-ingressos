package com.compass.ms_event_manager.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Document(collection = "events")
public class Event {

    @Id
    private String id;

    @NotBlank(message = "O nome do evento é obrigatório")
    private String name;

    @NotBlank(message = "A descrição do evento é obrigatória")
    private String description;

    private String location;
    private String date;

    
}

