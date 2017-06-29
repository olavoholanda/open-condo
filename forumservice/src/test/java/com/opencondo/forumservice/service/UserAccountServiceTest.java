package com.opencondo.forumservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.opencondo.forumservice.ForumserviceApplication;
import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.domain.repository.UserAccountRepository;
import com.opencondo.forumservice.utils.SwaggerConfig;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * <code>UserAccountService</code> test class. Tests the create, retrieve and update services
 * mocking the underlying repository. The delete method was not tested, because when the repository
 * is mocked, almost no code remains.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ForumserviceApplication.class)
public class UserAccountServiceTest {

  @MockBean
  private UserAccountRepository repository;

  @MockBean
  private UserAccountQueryService query;

  @Autowired
  private UserAccountService service;

  @Test
  public void createUser() throws Exception {

    String externalId = "newUser1";
    String name = "New User";

    UserAccount mocked = new UserAccount(externalId, name);
    mocked.setId(1L);

    given(this.repository.save(any())).willReturn(mocked);

    UserAccount accountResult = this.service.createUser(externalId, name);

    assertEquals(externalId, accountResult.getExternalId());
    assertEquals(name, accountResult.getName());
  }

  @Test
  public void retrieveUser() throws Exception {
    String externalId = "newUser1";
    String name = "New User";
    Long id = 1L;

    UserAccount mocked = new UserAccount(externalId, name);
    mocked.setId(id);

    given(this.repository.findById(id)).willReturn(Optional.of(mocked));

    UserAccount accountResult = this.service.retrieveUser(id);

    assertEquals(externalId, accountResult.getExternalId());
    assertEquals(name, accountResult.getName());
  }

  @Test
  public void updateUserAccountNameByExternalId() throws Exception {
    String externalId = "newUser1";
    String name = "New User Name";
    Long id = 1L;

    UserAccount mocked = new UserAccount(externalId, name);
    mocked.setId(id);

    given(this.query.getUserByExternalId(externalId)).willReturn(mocked);
    given(this.repository.save(any())).willReturn(mocked);

    UserAccount accountResult = this.service.updateUserAccountNameByExternalId(externalId, name);

    assertEquals(externalId, accountResult.getExternalId());
    assertEquals(name, accountResult.getName());
  }
}