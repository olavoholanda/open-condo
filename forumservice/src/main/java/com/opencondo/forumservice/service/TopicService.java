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

  private final UserAccountQueryService accountQueryService;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public TopicService(TopicRepository repository, UserAccountQueryService accountQueryService) {
    this.repository = repository;
    this.accountQueryService = accountQueryService;
  }

  /**
   * Creates a new <code>Topic</code> based on the title and author. The topic
   * create time will be the moment that the object is created and by default it
   * is created with open status as true.
   *
   * @param title the <code>String</code> with topic's title.
   * @param username the <code>String</code> username of the author.
   * @param condoId the <code>String</code> condo id of the topic.
   * @return a new, persisted, topic.
   */
  public Topic createTopic(String title, String username, String condoId) {
    UserAccount author = accountQueryService.getUserByUsername(username);

    Topic topic = new Topic(title, new Date(), author, condoId);
    return repository.save(topic);
  }

  /**
   * Retrieves the desired <code>Topic</code> having the passed id.
   *
   * @param id the topic <code>Long</code> id.
   * @return the desired topic.
   */
  //TODO: throw new exception for not found and remove this null
  public Topic retrieveTopic(Long id) {
    return repository.findOne(id);
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
   * Updates the desired <code>Topic</code> to a closed
   * status.
   *
   * @param id the message <code>Long</code> id.
   * @return the updated desired topic.
   */
  public Topic closeTopic(Long id) {
    Topic topic = this.retrieveTopic(id);
    topic.setOpen(Boolean.FALSE);

    return repository.save(topic);
  }

  /**
   * Updates the desired <code>Topic</code> to a open
   * status.
   *
   * @param id the message <code>Long</code> id.
   * @return the updated desired topic.
   */
  public Topic openTopic(Long id) {
    Topic topic = this.retrieveTopic(id);
    topic.setOpen(Boolean.TRUE);

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
