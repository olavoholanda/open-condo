package com.opencondo.forumservice.domain.repository;

import com.opencondo.forumservice.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The <code>UserAccountRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional account related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on user accounts.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

  /**
   * Searches an <code>UserAccount</code> by its external identification. Remember
   * that the global project uses a micro service architecture, so, this external id is
   * the real id of the User, managed by the account micro service.
   *
   * @param externalId the String representing the user external id
   */
  UserAccount findByExternalId(String externalId);
}
