package com.example.security.controller;

import com.example.security.model.Users;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        return service.verify(users);
    }

    @GetMapping("/")
    public List<Map<String, String>> fetchData() {
        List<Map<String, String>> result = new ArrayList<>();

        Map<String, String> user1 = new HashMap<>();
        user1.put("name", "John Doe");
        user1.put("email", "john@example.com");

        Map<String, String> user2 = new HashMap<>();
        user2.put("name", "Jane Smith");
        user2.put("email", "jane@example.com");

        result.add(user1);
        result.add(user2);

        return result;
    }



}
