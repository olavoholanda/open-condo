package com.opencondo.forumservice.controller;

import static org.junit.Assert.*;

import com.opencondo.forumservice.ForumserviceApplication;
import com.opencondo.forumservice.controller.dto.UserDTO;
import com.opencondo.forumservice.domain.model.UserAccount;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <code>UserAccountController</code> test class. Tests the operations of the User resource controller.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ForumserviceApplication.class)
@Transactional
public class UserAccountControllerTest {

  @Autowired
  private UserAccountController controller;

  @Test
  public void createUser() throws Exception {
    String username = "user1";
    String name = "User 1";
    UserDTO dto = new UserDTO();
    dto.buildFromEntity(new UserAccount(username, name));
    UserDTO result = controller.createUser(dto);

    assertEquals(username, result.getUsername());
    assertEquals(name, result.getName());
  }

  @Test
  public void getUser() throws Exception {
    String username = "user2";
    String name = "User 2";
    UserDTO dto = new UserDTO();
    dto.buildFromEntity(new UserAccount(username, name));
    controller.createUser(dto);

    UserDTO result = controller.getUser(username);

    assertEquals(username, result.getUsername());
    assertEquals(name, result.getName());
  }

  @Test(expected = NullPointerException.class)
  public void deleteUser() throws Exception {
    String username = "user3";
    String name = "User 3";
    UserDTO dto = new UserDTO();
    dto.buildFromEntity(new UserAccount(username, name));
    controller.createUser(dto);

    controller.deleteUser(username);

    controller.getUser(username);
  }

  @Test
  public void updateUser() throws Exception {
    String username = "user4";
    String name = "User 4";
    UserDTO dto = new UserDTO();
    dto.buildFromEntity(new UserAccount(username, name));
    controller.createUser(dto);

    name = "User New 4";
    dto.buildFromEntity(new UserAccount(username, name));

    UserDTO result = controller.updateUser(dto);

    assertEquals(username, result.getUsername());
    assertEquals(name, result.getName());
  }

}