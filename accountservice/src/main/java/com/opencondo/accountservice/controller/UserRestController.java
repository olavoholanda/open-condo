package com.opencondo.accountservice.controller;

import com.opencondo.accountservice.controller.dto.UserDTO;
import com.opencondo.accountservice.domain.model.User;
import com.opencondo.accountservice.service.UserQueryService;
import com.opencondo.accountservice.service.UserService;
import com.opencondo.accountservice.service.exception.EntityNotFoundException;
import com.opencondo.accountservice.service.exception.InvalidInputException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Rest endpoint for user resource, including CRUD and query operations.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

  private final UserService service;
  private final UserQueryService queryService;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public UserRestController(UserQueryService queryService, UserService service) {
    this.queryService = queryService;
    this.service = service;
  }

  /**
   * Creates a new admin user with the properties added in the user instance.
   *
   * @param dto the UserDTO instance, must have the following properties with value: name, username
   * and password. Optional: address and email. Id, condoId and Role if have values will be
   * ignored.
   * @return a new persisted UserDTO.
   */
//  @Override
  @RequestMapping(value = "/admin", method = RequestMethod.POST)
  public UserDTO createAdminUser(@RequestBody UserDTO dto) {
    UserDTO resultDTO = new UserDTO();

    try {
      User user = service.createAdminUser(dto.createEntity());
      resultDTO.buildFromEntity(user);
    } catch (InvalidInputException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return resultDTO;
  }

  /**
   * Creates a new resident user with the properties added in the user instance.
   *
   * @param dto the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
//  @Override
  @RequestMapping(method = RequestMethod.POST)
  public UserDTO createResidentUser(@RequestBody UserDTO dto) {
    UserDTO resultDTO = new UserDTO();

    try {
      User user = service.createResidentUser(dto.createEntity());
      resultDTO.buildFromEntity(user);
    } catch (InvalidInputException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return resultDTO;
  }

  /**
   * Creates a new manager user with the properties added in the user instance.
   *
   * @param dto the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
//  @Override
  @RequestMapping(value = "/manager", method = RequestMethod.POST)
  public UserDTO createManagerUser(@RequestBody UserDTO dto) {
    UserDTO resultDTO = new UserDTO();

    try {
      User user = service.createManagerUser(dto.createEntity());
      resultDTO.buildFromEntity(user);
    } catch (InvalidInputException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return resultDTO;
  }

  /**
   * Creates a new doorman user with the properties added in the user instance.
   *
   * @param dto the UserDTO instance, must have the following properties with value: name, username,
   * password, condoId. Optional: address and email. Id and Role if have values will be ignored.
   * @return a new persisted UserDTO.
   */
//  @Override
  @RequestMapping(value = "/doorman", method = RequestMethod.POST)
  public UserDTO createDoormanUser(@RequestBody UserDTO dto) {
    UserDTO resultDTO = new UserDTO();

    try {
      User user = service.createDoormanUser(dto.createEntity());
      resultDTO.buildFromEntity(user);
    } catch (InvalidInputException e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    return resultDTO;
  }

  /**
   * Retrieves the desired user.
   *
   * @param username the String username.
   * @return the desired UserDTO.
   */
//  @Override
  @RequestMapping(path = "/{username}", method = RequestMethod.GET)
  public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username) {
    UserDTO resultDTO = new UserDTO();
    Optional<User> optional = queryService.getUserByUsername(username);

    if (optional.isPresent()) {
      resultDTO.buildFromEntity(optional.get());
    } else {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(resultDTO);
  }

  /**
   * Deletes the desired user.
   *
   * @param username the String username.
   */
//  @Override
  @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
  public ResponseEntity deleteUser(@PathVariable("username") String username) {
    try {
      service.deleteUser(username);
      return ResponseEntity.ok().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Updates the desired user.
   *
   * @param dto an <code>UserDTO</code> with username and the new name, or new address or new
   * email.
   * @return the UserDTO updated.
   */
//  @Override
  @RequestMapping(method = RequestMethod.PUT)
  public UserDTO updateUser(@RequestBody UserDTO dto) {
    UserDTO resultDTO = new UserDTO();
    try {
      User user = service.updateUser(dto.createEntity());
      resultDTO.buildFromEntity(user);
    } catch (EntityNotFoundException e) {
      throw new HttpServerErrorException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return resultDTO;
  }
}
