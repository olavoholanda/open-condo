package com.opencondo.accountservice.service;


import com.opencondo.accountservice.domain.model.User;

import java.util.Optional;

/**
 * User service interface for queries regarding the user resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public interface UserQueryService {

  /**
   * Searches an user with a specified username.
   *
   * @param username the specified username.
   * @return an optional with an user if found or null otherwise.
   */
  Optional<User> getUserByUsername(String username);

  /**
   * Searches an user with a specified email.
   *
   * @param email the specified email.
   * @return an optional with an user if found or null otherwise.
   */
  Optional<User> getUserByEmail(String email);
}
