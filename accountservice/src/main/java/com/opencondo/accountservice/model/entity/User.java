package com.opencondo.accountservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Name can not be null")
    private String name;

    private String email;

    @NotNull(message = "Username can not be null")
    private String username;

    @NotNull(message = "Password can not be null")
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
