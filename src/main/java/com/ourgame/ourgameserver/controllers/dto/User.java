package com.ourgame.ourgameserver.controllers.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
