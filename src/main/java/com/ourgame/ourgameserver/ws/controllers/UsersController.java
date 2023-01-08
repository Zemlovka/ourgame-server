package com.ourgame.ourgameserver.ws.controllers;

import com.ourgame.ourgameserver.ws.controllers.dto.User;
import com.ourgame.ourgameserver.ws.model.user.UserService;
import com.ourgame.ourgameserver.ws.security.service.TokenService;
import com.ourgame.ourgameserver.ws.controllers.dto.StringPlusImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UsersController {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);
    private final TokenService tokenService;
    private final UserService userService;
    private PasswordEncoder encoder;

    public UsersController(TokenService tokenService, UserService userService) {
        userService.saveUser(new User("user",  "password", new SimpleGrantedAuthority("ROLE_USER")));
        this.tokenService = tokenService;
        this.userService = userService;

    }

    @CrossOrigin
    @GetMapping("/token")
    public String getToken(Authentication authentication) {
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
    @GetMapping("/stats")
    public ResponseEntity<StringPlusImage> displayStats(Authentication authentication) {
        return ResponseEntity.ok(new StringPlusImage(authentication.getName(), "https://lh3.googleusercontent.com/aPeSCag8eHV8Xfsu2FdRzRrV0KzD3CWkO8jGbvGZSFIvA_5-8BJ6cHh0lkvqXeUYFwDRp03pH3HdqMNv9--Pv_jw0z1USaKyjg=s400"));
    }

    @CrossOrigin
    @GetMapping("/connect-socket")
    public ResponseEntity<String> connectSocket(Authentication authentication) {
        return ResponseEntity.ok("Connected");
    }

}
