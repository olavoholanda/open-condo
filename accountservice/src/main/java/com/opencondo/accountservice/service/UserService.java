package com.opencondo.accountservice.service;


import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.service.exception.EntityNotFoundException;
import com.opencondo.accountservice.service.exception.InvalidInputException;
import org.springframework.stereotype.Service;

/**
 * User service interface for CRUD operations regarding the user resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public interface UserService {

  /**
   * Creates a new admin user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username
   * and password. Optional: address and email. Id, condoId and Role if have values will be
   * ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  User createAdminUser(User user) throws InvalidInputException;

  /**
   * Creates a new resident user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  User createResidentUser(User user) throws InvalidInputException;

  /**
   * Creates a new manager user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  User createManagerUser(User user) throws InvalidInputException;

  /**
   * Creates a new doorman user with the properties added in the user instance.
   *
   * @param user the User instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted User.
   * @throws InvalidInputException if some input is invalid.
   */
  User createDoormanUser(User user) throws InvalidInputException;

  /**
   * Retrieves an user with the specified id.
   *
   * @param id the user Long id.
   * @return the wanted User.
   * @throws EntityNotFoundException if the user was not found.
   */
  User retrieveUser(Long id) throws EntityNotFoundException;

  /**
   * Updates an user passing a modified user instance. Properties that can be updated here:
   * name, email and address.
   *
   * @param user the modified user instance.
   * @return the updated User.
   * @throws EntityNotFoundException if the user was not found.
   */
  User updateUser(User user) throws Exception;

  /**
   * Deletes an user with the specified id.
   *
   * @param id the user Long id.
   * @throws EntityNotFoundException if the user was not found.
   */
  void deleteUser(Long id) throws EntityNotFoundException;
}
