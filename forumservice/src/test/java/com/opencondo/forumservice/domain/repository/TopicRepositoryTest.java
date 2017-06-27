package com.opencondo.forumservice.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.opencondo.forumservice.domain.model.Topic;
import com.opencondo.forumservice.domain.model.UserAccount;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <code>TopicRepository</code> test class. The only test here is for
 * the <code>findAllByOrderByCreateTimeDesc</code> operation, with this test we can check if
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
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TopicRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TopicRepository repository;

  @Test
  public void testFindAllByOrderByCreateTimeDesc() throws Exception {
    //creates an user, that will be the author
    String externalId = "sampleTestId1";
    String name = "John Doe";
    String condoId = "condo1";
    UserAccount author = new UserAccount(externalId, name);
    author = this.entityManager.persist(author);
    this.entityManager.flush();

    //persists 3 topics, from the oldest to the newest
    this.entityManager.persist(new Topic("post title 1", new Date(1497069005256L), author, condoId));
    this.entityManager.persist(new Topic("post title 2", new Date(1497569005256L), author, condoId));
    this.entityManager.persist(new Topic("post title 3", new Date(1497969005256L), author, condoId));
    this.entityManager.flush();

    //gets the topics in order by creation time
    List<Topic> topics = this.repository.findByCondoId(condoId, PageRequest
        .of(0,10, Sort.Direction.ASC,"createTime"));

    //asserts
    assertThat(topics.get(0).getTitle()).isEqualTo("post title 3");
    assertThat(topics.get(2).getTitle()).isEqualTo("post title 1");
    assertThat(topics.get(0).getAuthor().getName()).isEqualTo("John Doe");
    assertThat(topics.size()).isEqualTo(3);
  }
}
