package com.opencondo.accountservice.controller;

import com.opencondo.accountservice.controller.dto.UserDTO;

/**
 * Interface that provides useful endpoint operations of this project
 * regarding the User resource.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public interface UserController {

  /**
   * Creates a new admin user with the properties added in the user instance.
   *
   * @param user the UserDTO instance, must have the following properties with value: name, username
   * and password. Optional: address and email. Id, condoId and Role if have values will be
   * ignored.
   * @return a new persisted UserDTO.
   */
  UserDTO createAdminUser(UserDTO user);

  /**
   * Creates a new resident user with the properties added in the user instance.
   *
   * @param user the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
  UserDTO createResidentUser(UserDTO user);

  /**
   * Creates a new manager user with the properties added in the user instance.
   *
   * @param user the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
  UserDTO createManagerUser(UserDTO user);

  /**
   * Creates a new doorman user with the properties added in the user instance.
   *
   * @param user the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
  UserDTO createDoormanUser(UserDTO user);

  /**
   * Retrieves the desired user.
   *
   * @param username the String username.
   * @return the desired UserDTO.
   */
  UserDTO getUser(String username);

  /**
   * Deletes the desired user.
   *
   * @param username the String username.
   */
  void deleteUser(String username);

  /**
   * Updates the desired user.
   *
   * @param dto an <code>UserDTO</code> with username and the new name.
   * @return the UserDTO updated.
   */
  UserDTO updateUser(UserDTO dto);
}
