package com.ourgame.ourgameserver.controllers;

import com.ourgame.ourgameserver.controllers.dto.*;
import com.ourgame.ourgameserver.security.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @GetMapping("/token")
    public String token(Authentication authentication) {
        LOG.info("token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("token generated: '{}'", token);
        System.out.println("Request token for user: " + authentication.getName());
        return token;
    }


    /**
     * Test get mapping, used for connection testing
     * @return always 200 code + test string
     */
    @CrossOrigin
    @GetMapping("/test")
    public ResponseEntity<StringPlusImage> test(Authentication authentication) {
        return ResponseEntity.ok(new StringPlusImage(authentication.getName(), "https://lh3.googleusercontent.com/aPeSCag8eHV8Xfsu2FdRzRrV0KzD3CWkO8jGbvGZSFIvA_5-8BJ6cHh0lkvqXeUYFwDRp03pH3HdqMNv9--Pv_jw0z1USaKyjg=s400"));
    }
}
