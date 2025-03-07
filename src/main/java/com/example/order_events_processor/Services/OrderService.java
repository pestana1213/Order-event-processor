package com.example.order_events_processor.Services;

import com.example.order_events_processor.DTO.OrderDto;
import com.example.order_events_processor.Documents.Orders;
import com.example.order_events_processor.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Orders createOrder(OrderDto orderDto) {
        Orders order = new Orders(orderDto);
        return orderRepo.save(order);
    }
}
