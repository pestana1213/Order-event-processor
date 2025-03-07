package com.example.order_events_processor.Services;

import com.example.order_events_processor.DTO.UserDto;
import com.example.order_events_processor.Documents.User;
import com.example.order_events_processor.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers() {
        return  userRepo.findAll();
    }

    public User createUser(UserDto userDto)  {
        return userRepo.save(new User(userDto.name()));
    }
}
