package com.compass.ms_ticket_manager.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
public class Ticket {

    @Id
    private String ticketId;
    private String cpf;
    private String customerName;
    private String customerMail;
    private Event event;
    private String brlTotalAmount;
    private String usdTotalAmount;
    private String status;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getBrlTotalAmount() {
        return brlTotalAmount;
    }

    public void setBrlTotalAmount(String brlTotalAmount) {
        this.brlTotalAmount = brlTotalAmount;
    }

    public String getUsdTotalAmount() {
        return usdTotalAmount;
    }

    public void setUsdTotalAmount(String usdTotalAmount) {
        this.usdTotalAmount = usdTotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}



