package com.ourgame.ourgameserver.controllers;

import com.ourgame.ourgameserver.controllers.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api")
public class UsersController {
    Map<String, String> testUserMap = new HashMap<>();

    public UsersController() {
        testUserMap.put("test", "test");
    }

    /**
     * Test get mapping, used for connection testing
     * @return always 200 code + test string
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    /**
     * Test post mapping, used for connection testing
     * @return always 200 code + test string
     */
    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser() {
        return ResponseEntity.ok("User registered");
    }


    @PostMapping(path = "/login")
    public ResponseEntity<String> loginUser(User user) {
        if (testUserMap.containsKey(user.getUsername()) && testUserMap.get(user.getUsername()).equals(user.getPassword())) {
            return ResponseEntity.ok("User logged in");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
