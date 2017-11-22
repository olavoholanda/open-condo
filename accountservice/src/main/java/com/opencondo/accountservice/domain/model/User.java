package com.opencondo.accountservice.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents an unique user in the system.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
@Setter
public class User {

    @Id
    private String id;

    @NotNull(message = "Name can not be null")
    private String name;

    private String email;

    private Date createTime;

    @NotNull(message = "Username can not be null")
    private String username;

    @NotNull(message = "Password can not be null")
    private String password;

    private String condoId;

    @NotNull(message = "Role can not be null")
    private Role role;

    private String address;

    private boolean enable = true;

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, name='%s', username='%s', email='%s']",
                id, name, username, email);
    }
}
