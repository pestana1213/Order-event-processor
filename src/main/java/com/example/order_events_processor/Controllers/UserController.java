package com.example.order_events_processor.Controllers;

// Note: this userController in a perfect world would be in a different repo.
// Maybe in the future I will do so

import com.example.order_events_processor.DTO.UserDto;
import com.example.order_events_processor.Documents.User;
import com.example.order_events_processor.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User creteUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}
