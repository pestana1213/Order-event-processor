package com.example.order_events_processor.Services;

import com.example.order_events_processor.DTO.OrderDto;
import com.example.order_events_processor.DTO.OrderStatus;
import com.example.order_events_processor.Documents.Orders;
import com.example.order_events_processor.Documents.User;
import com.example.order_events_processor.Exceptions.OrderException;
import com.example.order_events_processor.Exceptions.UserNotFound;
import com.example.order_events_processor.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserService userService;

    private Orders getOrder(String orderId) {
        Optional<Orders> order = orderRepo.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderException("Order not found");
    }

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

    @Transactional
    public void updateStatus(String orderId, String nextStatus) {
        try {
            Orders order = getOrder(orderId);
            if(isNextStatusValid(order.getStatus(), nextStatus)) {
                order.setStatus(nextStatus);
                orderRepo.save(order);
            }
            else {
                throw new OrderException("Invalid status");
            }
        }
        catch (Exception e) {
            throw new OrderException("Couldn't update order: " + e.getMessage());
        }
    }

    private boolean isNextStatusValid(String current, String next) {
        try {
            OrderStatus statusEnum = OrderStatus.valueOf(next.toUpperCase());
            OrderStatus currentEnum = OrderStatus.valueOf(current.toUpperCase());

            switch (currentEnum) {
                case CREATED -> {
                    return statusEnum.equals(OrderStatus.PROCESSING);
                }
                case PROCESSING -> {
                    return statusEnum.equals(OrderStatus.PROCESSED);
                }
                case PROCESSED -> {
                    return statusEnum.equals(OrderStatus.SENT);
                }
                case SENT -> {
                    return statusEnum.equals(OrderStatus.RECEIVED);
                }
                default -> {
                    return false;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
    }
}
