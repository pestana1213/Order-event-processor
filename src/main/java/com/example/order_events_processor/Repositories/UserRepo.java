package com.example.order_events_processor.Repositories;

import com.example.order_events_processor.Documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
