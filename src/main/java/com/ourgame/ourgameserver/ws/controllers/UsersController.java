package com.ourgame.ourgameserver.ws.controllers;

import com.ourgame.ourgameserver.ws.controllers.dto.LobbyDto;
import com.ourgame.ourgameserver.ws.controllers.dto.UserDto;
import com.ourgame.ourgameserver.ws.controllers.exceptions.CustomErrorMessage;
import com.ourgame.ourgameserver.ws.model.exceptions.UserAlreadyExistsException;
import com.ourgame.ourgameserver.ws.model.user.UserService;
import com.ourgame.ourgameserver.ws.security.service.TokenService;
import com.ourgame.ourgameserver.ws.controllers.dto.StringPlusImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UsersController {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);
    private final TokenService tokenService;
    private final UserService userService;

    public UsersController(TokenService tokenService, UserService userService) {
        try {
            userService.saveUser(new UserDto("user",  "password", new SimpleGrantedAuthority("ROLE_USER")));
        } catch (UserAlreadyExistsException e) {}
        try {
            userService.saveUser(new UserDto("user1",  "password", new SimpleGrantedAuthority("ROLE_USER")));
        } catch (UserAlreadyExistsException e) {}
        try {
            userService.saveUser(new UserDto("user2",  "password", new SimpleGrantedAuthority("ROLE_USER")));
        } catch (UserAlreadyExistsException e) {}
        this.tokenService = tokenService;
        this.userService = userService;
    }


    /**
     * This method is used to register a new user
     * @throws UserAlreadyExistsException in case user with this username already exists
     * @param userDto user to be registered
     * @return ResponseEntity with status code and token in body
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userDto.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));
        userService.saveUser(userDto);
        LOG.info("Registering user {}", userDto);
        return ResponseEntity.ok("User registered");
    }

    @GetMapping("/token")
    public String getToken(Authentication authentication) {
        LOG.info("token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("token generated: '{}'", token);
        System.out.println("Request token for user: " + authentication.getName());
        return token;
    }


    @GetMapping("/stats")
    public ResponseEntity<StringPlusImage> displayStats(Authentication authentication) {
        return ResponseEntity.ok(new StringPlusImage(authentication.getName(), "https://lh3.googleusercontent.com/aPeSCag8eHV8Xfsu2FdRzRrV0KzD3CWkO8jGbvGZSFIvA_5-8BJ6cHh0lkvqXeUYFwDRp03pH3HdqMNv9--Pv_jw0z1USaKyjg=s400"));
    }


    @GetMapping("/connect-socket")
    public ResponseEntity<String> connectSocket(Authentication authentication) {
        return new ResponseEntity<>("new ", HttpStatus.SWITCHING_PROTOCOLS);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomErrorMessage> handleQuizNotFoundException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(
                new CustomErrorMessage(HttpStatus.BAD_REQUEST.value(),
                        e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
