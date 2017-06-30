package com.opencondo.accountservice.controller.dto;

import com.opencondo.accountservice.domain.model.Role;
import com.opencondo.accountservice.domain.model.User;
import lombok.Getter;

/**
 * Data Transfer Object for the User Entity.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
public class UserDTO implements DTOMapper<User> {

  private Long id;
  private String name;
  private String email;
  private String username;
  private String password;
  private String condoId;
  private String role;
  private String address;
  private boolean enable = true;

  /**
   * Fills the DTO with information from the model.
   *
   * @param user the <code>User</code> model.
   */
  @Override
  public void buildFromEntity(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.condoId = user.getCondoId();
    this.role = user.getRole().name();
    this.address = user.getAddress();
    this.enable = user.isEnable();
  }

  /**
   * Creates a model entity from the DTO.
   *
   * @return the user parsed from this DTO.
   */
  @Override
  public User createEntity() {
    User user = new User();
    user.setId(this.id);
    user.setAddress(this.address);
    user.setEmail(this.email);
    user.setName(this.name);
    user.setRole(Role.valueOf(this.role));
    user.setPassword(this.password);
    user.setEnable(this.enable);
    user.setCondoId(this.condoId);
    user.setUsername(this.username);

    return user;
  }
}
