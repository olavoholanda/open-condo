package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.domain.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The <code>UserAccountQueryService</code> class is responsible for query methods regarding
 * user accounts. For CRUD operations look for <code>UserAccountService</code>.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class UserAccountQueryService {

  private final UserAccountRepository repository;

  /**
   * Class constructor with AutoWired dependency injection.
   */
  @Autowired
  public UserAccountQueryService(UserAccountRepository repository) {
    this.repository = repository;
  }

  /**
   * Searches for an <code>UserAccount</code> with the specific username.
   *
   * @param username the <code>String</code> username.
   * @return the desired user account.
   */
  public UserAccount getUserByUsername(String username) {
    return this.repository.findByUsername(username);
  }
}
