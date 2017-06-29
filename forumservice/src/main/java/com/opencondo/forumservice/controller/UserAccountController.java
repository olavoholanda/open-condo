package com.opencondo.forumservice.controller;

import com.opencondo.forumservice.controller.dto.UserDTO;
import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.service.UserAccountQueryService;
import com.opencondo.forumservice.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest endpoint for user account resource, including CRUD and query operations.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/api/user")
public class UserAccountController {

  private final UserAccountQueryService queryService;
  private final UserAccountService service;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public UserAccountController(UserAccountQueryService queryService, UserAccountService service) {
    this.queryService = queryService;
    this.service = service;
  }

  /**
   * Creates a new user.
   *
   * @param dto an <code>UserDTO</code> with username and name.
   * @return the created user.
   */
  @RequestMapping(method = RequestMethod.POST)
  public UserDTO createUser(@RequestBody UserDTO dto) {
    UserAccount account = this.service.createUser(dto.getUsername(), dto.getName());
    return this.parseUser(account);
  }

  /**
   * Retrieves the desired user.
   *
   * @param username the String username.
   * @return the desired user.
   */
  @RequestMapping(path = "/{username}", method = RequestMethod.GET)
  public UserDTO getUser(@PathVariable("username") String username) {
    UserAccount account = this.queryService.getUserByUsername(username);
    return this.parseUser(account);
  }

  /**
   * Deletes the desired user.
   *
   * @param username the String username.
   */
  @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable("username") String username) {
    this.service.deleteUser(username);
  }

  /**
   * Updates the desired user.
   *
   * @param dto an <code>UserDTO</code> with username and the new name.
   * @return the user updated.
   */
  @RequestMapping(method = RequestMethod.PUT)
  public UserDTO updateUser(@RequestBody UserDTO dto) {
    UserAccount account = this.service
        .updateUserAccountNameByExternalId(dto.getUsername(), dto.getName());
    return this.parseUser(account);
  }

  /**
   * Parses an entity model UserAccount to a UserDTO.
   *
   * @param account an <code>UserAccount</code> instance.
   * @return the user data transfer object.
   */
  private UserDTO parseUser(UserAccount account) {
    UserDTO dto = new UserDTO();
    dto.buildFromEntity(account);
    return dto;
  }
}
