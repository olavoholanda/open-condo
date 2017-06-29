package com.opencondo.accountservice.domain.storage;

import com.opencondo.accountservice.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The <code>UserRepository</code> interface extends Spring <code>MongoRepository</code
 * providing useful additional user related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on user accounts.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    /**
     * Finds an user with a specified username.
     *
     * @param username the specified username.
     * @return the user instance.
     */
    User findByUsername(String username);

    /**
     * Finds an user with a specified email.
     *
     * @param email the specified email.
     * @return the user instance.
     */
    User findByEmail(String email);
}
