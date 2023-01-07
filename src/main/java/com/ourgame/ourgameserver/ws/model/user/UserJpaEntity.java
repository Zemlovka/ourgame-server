package com.ourgame.ourgameserver.ws.model.user;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
class UserJpaEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "role", nullable = true)
    private GrantedAuthority authorities;

    @Column(name = "avatar", nullable = true)
    private File avatar;

    public UserJpaEntity(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserJpaEntity() {
    }

    public UserJpaEntity(String id, String password, GrantedAuthority authorities) {
        this.id = id;
        this.password = password;
        this.authorities = authorities;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(authorities);
    }


    public String getPassword() {
        return null;
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
