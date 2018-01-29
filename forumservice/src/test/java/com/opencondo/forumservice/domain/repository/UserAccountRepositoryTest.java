package com.opencondo.forumservice.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.opencondo.forumservice.domain.model.UserAccount;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <code>UserAccountRepository</code> test class. The only test here is for
 * the <code>findByExternalId</code> operation, with this test we can check if
 * there is any problem with the entities and their relationship. There is no need
 * for testing methods from Spring <code>JpaRepository</code>. Only creates test
 * here when the <code>UserAccountRepository</code> have custom implemented methods,
 * methods with <code>@Query</code> annotations and if you don't known for sure if the method
 * find you created with the Spring Data JPA is acting as you expect.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class UserAccountRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserAccountRepository repository;

  @Test
  public void testFindByExternalId() throws Exception {
    String username = "sampleTestId1";
    String name = "John Doe";
    this.entityManager.persist(new UserAccount(username, name));
    UserAccount user = this.repository.findByUsername(username);
    assertThat(user.getName()).isEqualTo(name);
  }
}
