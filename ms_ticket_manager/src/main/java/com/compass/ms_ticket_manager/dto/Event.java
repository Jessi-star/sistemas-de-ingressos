package com.compass.ms_ticket_manager.dto;

public class Event {
    private String id;
    private String name;
    private String description;
    private String location;
    private String eventdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String date) {
        this.eventdate = date;
    }
}

