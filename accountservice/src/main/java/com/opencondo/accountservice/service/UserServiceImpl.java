package com.opencondo.accountservice.service;

import com.opencondo.accountservice.domain.model.Role;
import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.domain.storage.UserRepository;
import com.opencondo.accountservice.service.exception.EntityNotFoundException;
import com.opencondo.accountservice.service.exception.InvalidInputException;
import com.opencondo.accountservice.utils.Cryptography;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User service concrete class that implements
 * {@link UserService} for CRUD operations regarding the user resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  private final UserQueryService query;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public UserServiceImpl(UserRepository repository, UserQueryService query) {
    this.repository = repository;
    this.query = query;
  }

  /**
   * Creates a new admin user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username
   * and password. Optional: address and email. Id, condoId and Role if have values will be
   * ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  @Override
  public User createAdminUser(User user) throws InvalidInputException {
    user.setRole(Role.ROLE_ADMIN);
    return createUser(user);
  }

  /**
   * Creates a new resident user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  @Override
  public User createResidentUser(User user) throws InvalidInputException {
    user.setRole(Role.ROLE_RESIDENT);
    return createUser(user);
  }

  /**
   * Creates a new manager user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  @Override
  public User createManagerUser(User user) throws InvalidInputException {
    user.setRole(Role.ROLE_MANAGER);
    return createUser(user);
  }

  /**
   * Creates a new doorman user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  @Override
  public User createDoormanUser(User user) throws InvalidInputException {
    user.setRole(Role.ROLE_DOORMAN);
    return createUser(user);
  }

  /**
   * Retrieves an user with the specified id.
   *
   * @param id the user Long id.
   * @return the wanted User.
   * @throws EntityNotFoundException if the user was not found.
   */
  @Override
  public User retrieveUser(Long id) throws EntityNotFoundException {
    Optional<User> optional = repository.findById(id);
    if(optional.isPresent()){
      return optional.get();
    }
    throw new EntityNotFoundException("User not found.");
  }

  /**
   * Updates an user passing a modified user instance. Properties that can be updated here:
   * name, email and address.
   *
   * @param user the modified user instance.
   * @return the updated User.
   * @throws EntityNotFoundException if the user was not found.
   */
  @Override
  public User updateUser(User user) throws EntityNotFoundException {
    //TODO: validates inputs
    Long id = user.getId();
    String name = user.getName();
    String email = user.getEmail();
    String address = user.getAddress();

    Optional<User> optional = repository.findById(id);
    if(optional.isPresent()){
      user =  optional.get();
      user.setName(name);
      user.setEmail(email);
      user.setAddress(address);

      return repository.save(user);
    }

    throw new EntityNotFoundException("User not found.");
  }

  /**
   * Deletes an user with the specified username.
   *
   * @param username the user String username.
   * @throws EntityNotFoundException if the user was not found.
   */
  @Override
  public void deleteUser(String username) throws EntityNotFoundException {
    Optional<User> optional = query.getUserByUsername(username);
    if(optional.isPresent()){
      User user =  optional.get();
      repository.delete(user);
    }

    throw new EntityNotFoundException("User not found.");
  }

  /**
   * Validates if an user instance has values for all required input
   * for creation. TODO: validates more than just for null
   *
   * @param user the user instance.
   * @throws InvalidInputException if some required property is null
   */
  private void validateRequiredInput(User user) throws InvalidInputException {
    if (user.getName() == null) {
      throw new InvalidInputException("Missing value for property 'name'.");
    }

    if (user.getUsername() == null) {
      throw new InvalidInputException("Missing value for property 'username'.");
    }

    if (user.getPassword() == null) {
      throw new InvalidInputException("Missing value for property 'password'.");
    }

    if (user.getCondoId() == null) {
      throw new InvalidInputException("Missing value for property 'condoId'.");
    }
  }

  /**
   * Validates if exists an user with the specified username or email.
   *
   * @param username the String username.
   * @param email the String email.
   * @throws InvalidInputException if exists user
   */
  private void validateUsernameAndEmail(String username, String email)
      throws InvalidInputException {
    Optional<User> opUsername = query.getUserByUsername(username);

    if (opUsername.isPresent()) {
      throw new InvalidInputException("Already exists user with this username.");
    }

    Optional<User> opEmail = query.getUserByEmail(email);

    if (opEmail.isPresent()) {
      throw new InvalidInputException("Already exists user with this email.");
    }
  }

  /**
   * Creates an user, adding runtime properties to the passed instance user.
   *
   * @param user the User instance to be created.
   * @return the new user.
   * @throws InvalidInputException if there is any invalid input.
   */
  private User createUser(User user) throws InvalidInputException {

    validateRequiredInput(user);
    validateUsernameAndEmail(user.getUsername(), user.getEmail());

    user.setEnable(Boolean.TRUE);
    user.setCreateTime(new Date());

    String password = user.getPassword();
    password = Cryptography.md5(password);
    user.setPassword(password);

    return repository.save(user);
  }


}
