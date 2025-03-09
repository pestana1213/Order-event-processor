package com.example.order_events_processor.MessagesMappers;

import com.example.order_events_processor.DTO.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class MessageToOrder implements MessageMapper{

    public OrderDto map(String message) {
        try {
            String[] parts = message.split("; ");
            String userId = parts[0].split(": ")[1];
            String product = parts[1].split(": ")[1];
            int quantity = Integer.parseInt(parts[2].split(": ")[1]);
            double total = Double.parseDouble(parts[3].split(": ")[1]);

            return new OrderDto(userId, product, quantity, total);
        }
        catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }

    public String orderIdFromMessage(String message) {
        try {
            String[] parts = message.split(" ");
            return parts[0];
        }
        catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }

    public String nextStatusFromMessage(String message) {
        try {
            String[] parts = message.split(" ");
            return parts[1];
        }
        catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }
}
