package com.opencondo.forumservice.domain.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * UserAccount is the entity class for representing simple information
 * about an user account. As this service is not responsible for managing
 * accounts, therefore, the info hold by this class is pretty basic and
 * for further info about this user, check the account service using the
 * username property. An UserAccount holds information about its username
 * and name.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
@Setter
@Entity
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false, length = 100)
  private String name;

  @OneToMany(mappedBy = "author")
  private List<Message> messages = new ArrayList<>();

  @OneToMany(mappedBy = "author")
  private List<Topic> topics = new ArrayList<>();

  /**
   * Protected constructor, please use the one with parameters, that are required
   * for this object.
   */
  protected UserAccount() {
  }

  /**
   * <code>UserAccount</code> constructor, creates a new user account instance with
   * the mandatory parameters.
   *
   * @param username the <code>String</code> representing the username.
   * @param name the <code>String</code> with the user's full name.
   */
  public UserAccount(String username, String name) {
    this.username = username;
    this.name = name;
  }
}
