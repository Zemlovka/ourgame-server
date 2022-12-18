package com.ourgame.ourgameserver.model.user;


import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class UserJpaEntity {
    @Id
    @Column(name = "username", nullable = false)
    private String id;

//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @Column(name = "email", nullable = true)
//    private String email;
//
//    @Column(name = "role", nullable = false)


}
