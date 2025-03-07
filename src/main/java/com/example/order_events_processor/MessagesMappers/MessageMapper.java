package com.example.order_events_processor.MessagesMappers;

import com.example.order_events_processor.DTO.OrderDto;

public interface MessageMapper {
    OrderDto map(String message);
}
