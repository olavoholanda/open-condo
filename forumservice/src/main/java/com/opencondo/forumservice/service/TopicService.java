package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.Topic;
import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.domain.repository.TopicRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The <code>TopicService</code> class is responsible for CRUD operations regarding
 * topics. For queries methods look for <code>TopicQueryService</code>. As a service
 * this class uses only its related repository, in this case <code>TopicRepository</code>,
 * operations on others entities are performed using the related services.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class TopicService {

  private final TopicRepository repository;

  private final UserAccountService userAccountService;

  @Autowired
  public TopicService(TopicRepository repository, UserAccountService userAccountService) {
    this.repository = repository;
    this.userAccountService = userAccountService;
  }

  /**
   * Creates a new <code>Topic</code> based on the title and author. The topic
   * create time will be the moment that the object is created and by default it
   * is created with open status as true.
   *
   * @param title the <code>String</code> with topic's title.
   * @param userExternalId the <code>String</code> external id of the author.
   * @return a new, persisted, topic.
   */
  public Topic createTopic(String title, String userExternalId) {
    UserAccount author = userAccountService.getUserByExternalId(userExternalId);

    Topic topic = new Topic(title, new Date(), author);
    return repository.save(topic);
  }

  /**
   * Retrieves the desired <code>Topic</code> having the passed id.
   *
   * @param id the topic <code>Long</code> id.
   * @return the desired topic.
   */
  public Topic retrieveTopic(Long id) {
    Optional<Topic> optionalTopic = repository.findById(id);
    //TODO: throw new exception for not found and remove this null
    return optionalTopic.orElse(null);
  }

  /**
   * Updates the desired <code>Topic</code> having the passed id
   * with the new title.
   *
   * @param id the message <code>Long</code> id.
   * @param newTitle the new topic <code>String</code> title.
   * @return the updated desired topic.
   */
  public Topic updateTopicTitle(Long id, String newTitle) {
    Topic topic = this.retrieveTopic(id);

    topic.setTitle(newTitle);
    topic.setModified(Boolean.TRUE);
    topic.setUpdateTime(new Date());

    return repository.save(topic);
  }

  /**
   * Updates the desired <code>Topic</code> having the passed id
   * with the new status, open or closed.
   *
   * @param id the message <code>Long</code> id.
   * @param status the <code>Boolean</code> status of the topic, open or closed.
   * @return the updated desired topic.
   */
  public Topic updateTopicStatus(Long id, Boolean status) {
    Topic topic = this.retrieveTopic(id);
    topic.setOpen(status);

    return repository.save(topic);
  }

  /**
   * Deletes the desired <code>Topic</code> having the passed id. This will delete
   * all messages related to this topic too.
   *
   * @param id the topic <code>Long</code> id
   */
  public void deleteTopic(Long id) {
    Topic topic = this.retrieveTopic(id);
    repository.delete(topic);
  }


}
