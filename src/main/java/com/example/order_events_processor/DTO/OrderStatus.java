package com.example.order_events_processor.DTO;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    PROCESSED("processed"),
    SENT("sent"),
    RECEIVED("received");

    private String description;
    OrderStatus(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
