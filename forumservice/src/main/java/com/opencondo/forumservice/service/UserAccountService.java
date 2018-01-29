package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.domain.repository.UserAccountRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The <code>UserAccountService</code> class is responsible for CRUD operations regarding
 * user accounts. For queries methods look for <code>UserAccountQueryService</code>. As a service
 * this class uses only its related repository, in this case <code>UserAccountRepository</code>,
 * operations on others entities are performed using the related services.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class UserAccountService {

  private final UserAccountRepository repository;

  private final UserAccountQueryService queryService;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public UserAccountService(UserAccountRepository repository, UserAccountQueryService queryService) {
    this.repository = repository;
    this.queryService = queryService;
  }

  /**
   * Creates a new <code>UserAccount</code> based on the username and name.
   * TODO: Check if there is already an user with that username.
   *
   * @param username the <code>String</code> with the username.
   * @param name the <code>String</code> name of the user.
   * @return a new, persisted, user account.
   */
  public UserAccount createUser(String username, String name) {
    UserAccount user = new UserAccount(username, name);
    return repository.save(user);
  }

  /**
   * Retrieves the desired <code>UserAccount</code> having the passed id.
   *
   * @param id the user account <code>Long</code> id.
   * @return the desired user account.
   */
  //TODO: throw new exception for not found and remove this null
  UserAccount retrieveUser(Long id) {
    return repository.findOne(id);
  }

  /**
   * Updates the desired <code>UserAccount</code> having the username
   * with the new name.
   *
   * @param username the <code>String</code> username.
   * @param newName the <code>String</code> new name of the user.
   * @return the updated desired user account.
   */
  public UserAccount updateUserAccountNameByExternalId(String username, String newName) {
    UserAccount user = queryService.getUserByUsername(username);
    user.setName(newName);

    return repository.save(user);
  }

  /**
   * Deletes the desired <code>UserAccount</code> having the username.
   *
   * @param username the user <code>String</code> username.
   */
  public void deleteUser(String username) {
    UserAccount userAccount = queryService.getUserByUsername(username);
    repository.delete(userAccount);
  }
}
