package com.opencondo.forumservice.controller.dto;

import com.opencondo.forumservice.domain.model.UserAccount;
import lombok.Getter;

/**
 * Data Transfer Object for the UserAccount Entity
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
public class UserDTO implements DTOMapper<UserAccount> {

  private String id;
  private String name;

  @Override
  public String toString() {
    return String.format(
        "UserAccountDTO[id='%s', name='%s']",
        id, name);
  }

  /**
   * Fills the DTO with information from the <code>UserAccount</code> entity.
   *
   * @param account the <code>UserAccount</code> entity
   */
  @Override
  public void buildFromEntity(UserAccount account) {
    this.id = account.getExternalId();
    this.name = account.getName();
  }
}
