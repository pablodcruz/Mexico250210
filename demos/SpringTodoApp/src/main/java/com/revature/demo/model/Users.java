package com.revature.demo.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "password_hash", nullable = false, unique = true)
    private String passwordHash;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Todos> todos = new ArrayList<>();

    public Users(){

    }

    public Users(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public Users(Long id, String name, String passwordHash) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
