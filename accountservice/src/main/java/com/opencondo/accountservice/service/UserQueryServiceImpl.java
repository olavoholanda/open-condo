package com.opencondo.accountservice.service;

import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.domain.storage.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service concrete class that implements
 * {@link UserQueryService} for queries regarding the user resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

  private final UserRepository repository;

  /**
   * Class constructor with AutoWired dependency injection.
   */
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
    return Optional.ofNullable(repository.findByUsername(username));
  }

  /**
   * Searches an user with a specified email.
   *
   * @param email the specified email.
   * @return an optional with an user if found or null otherwise.
   */
  @Override
  public Optional<User> getUserByEmail(String email) {
    return Optional.ofNullable(repository.findByEmail(email));
  }
}
