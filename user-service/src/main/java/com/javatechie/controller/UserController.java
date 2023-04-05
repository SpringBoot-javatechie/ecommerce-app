package com.javatechie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.entity.User;
import com.javatechie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private int port;


    @PostMapping
    public User registerNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) throws JsonProcessingException {
        log.info("UserController::getUser request fetching user by id  {} ", userId);
        System.out.println("request is landed on port : " + port);
        User user = userService.getUser(userId);
        log.info("UserController::getUser fetching user response  {} ", new ObjectMapper().writeValueAsString(user));
        return user;

    }

    @PutMapping("/{userId}/{amount}")
    public User updateUserBalance(@PathVariable int userId, @PathVariable double amount) {
        return userService.updateAccountStatus(userId, amount);
    }
}
