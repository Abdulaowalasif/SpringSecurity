package com.example.security.controller;

import com.example.security.model.Users;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        // Fetch all users
        List<Users> users = service.getAllUser();

        // Check if a user with the same username/email already exists
        boolean exists = users.stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()));

        if (exists) {
            // Return error response
            return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict
                    .body("User already exists with email: " + user.getUsername());
        }

        // Save new user
        Users savedUser = service.register(user);

        return ResponseEntity.status(HttpStatus.CREATED) // 201 Created
                .body(savedUser);
    }


    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody Users users) {
        HashMap<String, String> accessToken = new HashMap<>();

        accessToken.put("access", service.verify(users));

        return ResponseEntity.ok(accessToken);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> fetchData() {
        return ResponseEntity.ok(service.getAllUser());
    }


}
