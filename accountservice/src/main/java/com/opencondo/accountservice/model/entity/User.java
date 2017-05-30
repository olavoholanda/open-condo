package com.opencondo.accountservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Represents a unique user in the system.
 */
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String condoId;
    private String role;

    public User() {}

    public User(String name, String email,
                String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name='%s', username='%s', email='%s']",
                id, name, username, email);
    }
}
