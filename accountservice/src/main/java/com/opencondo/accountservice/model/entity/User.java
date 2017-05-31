package com.opencondo.accountservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Represents a unique user in the system.
 *
 * @author Olavo Holanda
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
    private Role role;
    private Address address;

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name='%s', username='%s', email='%s']",
                id, name, username, email);
    }
}
