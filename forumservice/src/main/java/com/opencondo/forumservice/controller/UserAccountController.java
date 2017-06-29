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
   * @param dto an <code>UserDTO</code> with user external id and name.
   * @return the created user.
   */
  @RequestMapping(method = RequestMethod.POST)
  public UserDTO createUser(@RequestBody UserDTO dto) {
    UserAccount account = this.service.createUser(dto.getId(), dto.getName());
    return this.parseUser(account);
  }

  /**
   * Retrieves the desired user.
   *
   * @param id the String external user id.
   * @return the desired user.
   */
  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public UserDTO getUser(@PathVariable("id") String id) {
    UserAccount account = this.queryService.getUserByExternalId(id);
    return this.parseUser(account);
  }

  /**
   * Deletes the desired user.
   *
   * @param id the String external user id.
   * @return 200 if successful.
   */
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
    this.service.deleteUser(id);
    return ResponseEntity.ok().build();
  }

  /**
   * Updates the desired user.
   *
   * @param dto an <code>UserDTO</code> with user external id and the new name.
   * @return the user updated.
   */
  @RequestMapping(method = RequestMethod.PUT)
  public UserDTO updateUser(@RequestBody UserDTO dto) {
    UserAccount account = this.service
        .updateUserAccountNameByExternalId(dto.getId(), dto.getName());
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
