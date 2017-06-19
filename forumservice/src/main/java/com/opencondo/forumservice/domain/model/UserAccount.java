package com.opencondo.forumservice.domain.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * UserAccount is the entity class for representing simple information
 * about an user account. As this service is not responsible for managing
 * accounts, therefore, the info hold by this class is pretty basic and
 * for further info about this user, check the account service using the
 * externalId property. An UserAccount holds information about its externalId,
 * name and avatarURL.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
@Getter
@Setter
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String externalId;

  @Column(nullable = false, length = 100)
  private String name;

  private String avatarURL;

  protected UserAccount() {}

  public UserAccount(String externalId, String name, String avatarURL) {
    this.externalId = externalId;
    this.name = name;
    this.avatarURL = avatarURL;
  }
}
