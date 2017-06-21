package com.opencondo.forumservice.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.opencondo.forumservice.domain.model.Message;
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
 * <code>MessageRepository</code> test class. The only test here is for
 * the <code>findByTopicIdOrderByCreateTimeAsc</code> operation, with this test we can check if
 * there is any problem with the entities and their relationship. There is no need
 * for testing methods from Spring <code>JpaRepository</code>. Only creates test
 * here when the <code>MessageRepository</code> have custom implemented methods,
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
public class MessageRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private MessageRepository repository;

  @Test
  public void testFindByTopicIdOrderByCreateTimeAsc() throws Exception {
    //creates an user, that will be the author
    String externalId = "messageSampleTesteId1";
    String name = "John Doe";
    String avatarUrl = "/avatar/messageSampleTesteId1.png";
    UserAccount author = new UserAccount(externalId, name);
    author = this.entityManager.persist(author);
    this.entityManager.flush();

    //creates a topic, that will contain the messages
    String title = "topic title";
    Topic topic = new Topic(title, new Date(), author);
    topic = this.entityManager.persist(topic);
    this.entityManager.flush();

    //persists 3 messages, from the oldest to the newest
    this.entityManager.persist(new Message("message content 1", new Date(1497069005256L), topic, author));
    this.entityManager.persist(new Message("message content 2", new Date(1497569005256L), topic, author));
    this.entityManager.persist(new Message("message content 3", new Date(1497969005256L), topic, author));
    this.entityManager.flush();

    //gets the messages in order by creation time
    List<Message> messages = this.repository.findByTopicId(topic.getId(), PageRequest.of(0,10, Sort.Direction.ASC,"createTime"));

    //asserts
    assertThat(messages.get(0).getMessage()).isEqualTo("message content 1");
    assertThat(messages.get(2).getMessage()).isEqualTo("message content 3");
    assertThat(messages.get(0).getAuthor().getName()).isEqualTo("John Doe");
    assertThat(messages.get(0).getTopic().getTitle()).isEqualTo("topic title");
    assertThat(messages.size()).isEqualTo(3);
  }
}
