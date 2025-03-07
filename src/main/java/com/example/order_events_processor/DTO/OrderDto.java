package com.example.order_events_processor.DTO;

public record OrderDto(String userId, String product, int quantity, Double total) {
}
