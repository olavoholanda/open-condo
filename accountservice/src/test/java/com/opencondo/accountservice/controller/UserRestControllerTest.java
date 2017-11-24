package com.opencondo.accountservice.controller;

import static org.junit.Assert.*;

import com.opencondo.accountservice.AccountserviceApplication;
import com.opencondo.accountservice.controller.dto.UserDTO;
import com.opencondo.accountservice.domain.storage.UserRepository;
import com.opencondo.accountservice.service.UserQueryServiceImpl;
import com.opencondo.accountservice.service.UserServiceImpl;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Created by olavo on 24/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties
public class UserRestControllerTest {

  @Autowired
  private UserRestController userRestController;

  @Test
  public void createAdminUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("admin");
    dto.setName("User Admin");
    dto.setPassword("admin");
    dto.setEmail("admin@email.com");
    UserDTO adminUser = userRestController.createAdminUser(dto);

    assertEquals("ROLE_ADMIN", adminUser.getRole());
    assertEquals("admin", adminUser.getUsername());
    //password must be null
    assertNull(adminUser.getPassword());
    assertEquals("admin@email.com", adminUser.getEmail());
    assertEquals("User Admin", adminUser.getName());
  }

  @Test
  public void createResidentUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("resident");
    dto.setName("User Resident");
    dto.setPassword("resident");
    dto.setEmail("resident@email.com");
    UserDTO adminUser = userRestController.createResidentUser(dto);

    assertEquals("ROLE_RESIDENT", adminUser.getRole());
    assertEquals("resident", adminUser.getUsername());
    //password must be null
    assertNull(adminUser.getPassword());
    assertEquals("resident@email.com", adminUser.getEmail());
    assertEquals("User Resident", adminUser.getName());
  }

  @Test
  public void createManagerUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("manager");
    dto.setName("User Manager");
    dto.setPassword("manager");
    dto.setEmail("manager@email.com");
    UserDTO adminUser = userRestController.createManagerUser(dto);

    assertEquals("ROLE_MANAGER", adminUser.getRole());
    assertEquals("manager", adminUser.getUsername());
    //password must be null
    assertNull(adminUser.getPassword());
    assertEquals("manager@email.com", adminUser.getEmail());
    assertEquals("User Manager", adminUser.getName());
  }

  @Test
  public void createDoormanUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("doorman");
    dto.setName("User Doorman");
    dto.setPassword("doorman");
    dto.setEmail("doorman@email.com");
    UserDTO adminUser = userRestController.createDoormanUser(dto);

    assertEquals("ROLE_DOORMAN", adminUser.getRole());
    assertEquals("doorman", adminUser.getUsername());
    //password must be null
    assertNull(adminUser.getPassword());
    assertEquals("doorman@email.com", adminUser.getEmail());
    assertEquals("User Doorman", adminUser.getName());
  }

  @Test
  public void getUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("userget");
    dto.setName("User Get");
    dto.setPassword("userget");
    dto.setEmail("userget@email.com");
    userRestController.createResidentUser(dto);
    UserDTO user = userRestController.getUser("userget").getBody();

    assertEquals("ROLE_RESIDENT", user.getRole());
    assertEquals("userget", user.getUsername());
    //password must be null
    assertNull(user.getPassword());
    assertEquals("userget@email.com", user.getEmail());
    assertEquals("User Get", user.getName());
  }

  @Test
  public void deleteUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("userdel");
    dto.setName("User Del");
    dto.setPassword("userdel");
    dto.setEmail("userdel@email.com");
    userRestController.createResidentUser(dto);
    ResponseEntity<?> response = userRestController.deleteUser("userdel");
    assertEquals(200, response.getStatusCodeValue());
    ResponseEntity<UserDTO> responseEntity = userRestController.getUser("userdel");
    assertEquals(404, responseEntity.getStatusCodeValue());
  }

  @Test
  public void updateUser() throws Exception {
    UserDTO dto = new UserDTO();
    dto.setUsername("userupdate");
    dto.setName("User Resident");
    dto.setPassword("userupdate");
    dto.setEmail("userupdate@email.com");
    UserDTO user = userRestController.createResidentUser(dto);

    assertEquals("User Resident", user.getName());

    user.setName("User Update");

    user = userRestController.updateUser(user);
    assertEquals("User Update", user.getName());
  }

}