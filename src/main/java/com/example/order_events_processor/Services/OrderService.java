package com.example.order_events_processor.Services;

import com.example.order_events_processor.DTO.OrderDto;
import com.example.order_events_processor.Documents.Orders;
import com.example.order_events_processor.Documents.User;
import com.example.order_events_processor.Exceptions.OrderException;
import com.example.order_events_processor.Exceptions.UserNotFound;
import com.example.order_events_processor.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserService userService;

    public void createOrder(OrderDto orderDto) {
        try {
            User user = userService.getUserById(orderDto.userId());
            Orders order = new Orders(orderDto, user);
            orderRepo.save(order);
        }
        catch (UserNotFound e) {
            throw new OrderException("Couldn't create a new order, user not found");
        }
    }
}
