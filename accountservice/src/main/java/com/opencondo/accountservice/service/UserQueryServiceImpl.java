package com.opencondo.accountservice.service;

import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.domain.storage.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User service concrete class that implements
 * {@link UserQueryService} for queries regarding the user resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class UserQueryServiceImpl implements UserQueryService {

  private final UserRepository repository;

  @Autowired
  public UserQueryServiceImpl(UserRepository repository) {
    this.repository = repository;
  }

  /**
   * Searches an user with a specified username.
   *
   * @param username the specified username.
   * @return an optional with an user if found or null otherwise.
   */
  @Override
  public Optional<User> getUserByUsername(String username) {
    return Optional.of(repository.findByUsername(username));
  }

  /**
   * Searches an user with a specified email.
   *
   * @param email the specified email.
   * @return an optional with an user if found or null otherwise.
   */
  @Override
  public Optional<User> getUserByEmail(String email) {
    return Optional.of(repository.findByEmail(email));
  }
}
