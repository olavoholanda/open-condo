package com.opencondo.forumservice.service;

import com.opencondo.forumservice.ForumserviceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <code>UserAccountQueryService</code> test class. Tests the queries and custom 'find by' methods
 * mocking the underlying repository.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ForumserviceApplication.class)
public class UserAccountQueryServiceTest {

  //  @MockBean
//  private UserAccountRepository repository;
//
//  @Autowired
//  private UserAccountQueryService query;
//
  @Test
  public void getUserByExternalId() throws Exception {

//    String username = "newUser1";
//    String name = "New User";
//
//    UserAccount mocked = new UserAccount(username, name);
//    mocked.setId(1L);
//
//    given(this.repository.findByUsername(username)).willReturn(mocked);
//
//    UserAccount accountResult = this.query.getUserByUsername(username);
//
//    assertEquals(username, accountResult.getUsername());
//    assertEquals(name, accountResult.getName());
  }
}