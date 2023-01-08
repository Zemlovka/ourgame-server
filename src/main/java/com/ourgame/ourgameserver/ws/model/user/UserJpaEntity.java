package com.ourgame.ourgameserver.ws.model.user;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserJpaEntity implements UserDetails {
    @Id
    @Column(name = "username", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "role", nullable = false)
    private GrantedAuthority authorities;

    @Column(name = "avatar", nullable = true)
    private File avatar;


    public UserJpaEntity() {
    }

    public UserJpaEntity(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserJpaEntity(String id, String password, GrantedAuthority authorities) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;
    }

    public UserJpaEntity(UserDetails user) {
        this.id = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities().stream().findFirst().orElse(null);
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(authorities);
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return id;
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }

}


