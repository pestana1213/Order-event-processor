package com.example.order_events_processor.Repositories;

import com.example.order_events_processor.Documents.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Orders, String> {
}
