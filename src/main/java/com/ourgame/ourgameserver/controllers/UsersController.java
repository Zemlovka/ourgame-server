package com.ourgame.ourgameserver.controllers;

import com.ourgame.ourgameserver.controllers.dto.*;
import com.ourgame.ourgameserver.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UsersController {
    Map<String, String> testUserMap = new HashMap<>();
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);
    private final TokenService tokenService;

    public UsersController(TokenService tokenService) {
        this.tokenService = tokenService;
        testUserMap.put("test", "test");
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.info("token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("token generated: '{}'", token);
        return token;
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
