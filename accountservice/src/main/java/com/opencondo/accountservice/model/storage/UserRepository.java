package com.opencondo.accountservice.model.storage;

import com.opencondo.accountservice.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Put a description of the class here.
 * @author Olavo Holanda
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    List<User> findByName(String name);
}
