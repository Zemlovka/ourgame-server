package com.ourgame.ourgameserver.ws.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;

    private GrantedAuthority authorities;

    public UserDto() {
    }

    public UserDto(String username, String password, GrantedAuthority authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
