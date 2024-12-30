package com.compass.ms_ticket_manager.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "db_ticket")


public class Ticket {

    private String ticketId;
    private String customerName;
    private String cpf;
    private String customerMail;
    private String eventId;
    private String eventName;
    private String location;
    private String BRLamount;
    private String USDamount;
    private Event event;
    private String BRLtotalAmount;
    private String USDtotalAmount;
    private String status;
    private String date;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBRLamount() {
        return BRLamount;
    }

    public void setBRLamount(String BRLamount) {
        this.BRLamount = BRLamount;
    }

    public String getUSDamount() {
        return USDamount;
    }

    public void setUSDamount(String USDamount) {
        this.USDamount = USDamount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getBRLtotalAmount() {
        return BRLtotalAmount;
    }

    public void setBRLtotalAmount(String BRLtotalAmount) {
        this.BRLtotalAmount = BRLtotalAmount;
    }

    public String getUSDtotalAmount() {
        return USDtotalAmount;
    }

    public void setUSDtotalAmount(String USDtotalAmount) {
        this.USDtotalAmount = USDtotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static class Event {
        private String eventId;
        private String eventName;
        private LocalDateTime eventDateTime;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String uf;


        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public LocalDateTime getEventDateTime() {
            return eventDateTime;
        }

        public void setEventDateTime(LocalDateTime eventDateTime) {
            this.eventDateTime = eventDateTime;
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }
}

