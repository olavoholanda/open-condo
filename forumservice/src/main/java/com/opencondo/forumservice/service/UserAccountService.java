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
   * Creates a new <code>UserAccount</code> based on the external id and name.
   *
   * @param externalId the <code>String</code> external id of the user.
   * @param name the <code>String</code> name of the user.
   * @return a new, persisted, user account.
   */
  public UserAccount createUser(String externalId, String name) {
    UserAccount user = new UserAccount(externalId, name);
    return repository.save(user);
  }

  /**
   * Retrieves the desired <code>UserAccount</code> having the passed id.
   *
   * @param id the user account <code>Long</code> id.
   * @return the desired user account.
   */
  public UserAccount retrieveUser(Long id) {
    Optional<UserAccount> optionalTopic = repository.findById(id);
    //TODO: throw new exception for not found and remove this null
    return optionalTopic.orElse(null);
  }

  /**
   * Updates the desired <code>UserAccount</code> having the passed external id
   * with the new name.
   *
   * @param externalId the <code>String</code> external id of the user.
   * @param newName the <code>String</code> new name of the user.
   * @return the updated desired user account.
   */
  public UserAccount updateUserAccountNameByExternalId(String externalId, String newName) {
    UserAccount user = queryService.getUserByExternalId(externalId);
    user.setName(newName);

    return repository.save(user);
  }

  /**
   * Deletes the desired <code>UserAccount</code> having the external id.
   *
   * @param externalId the user <code>String</code> external id.
   */
  public void deleteUser(String externalId) {
    UserAccount userAccount = queryService.getUserByExternalId(externalId);
    repository.delete(userAccount);
  }
}
