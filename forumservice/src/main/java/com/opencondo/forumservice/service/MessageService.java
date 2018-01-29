package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.Message;
import com.opencondo.forumservice.domain.model.Topic;
import com.opencondo.forumservice.domain.model.UserAccount;
import com.opencondo.forumservice.domain.repository.MessageRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The <code>MessageService</code> class is responsible for CRUD operations regarding
 * messages. For queries methods look for <code>MessageQueryService</code>. As a service
 * this class uses only its related repository, in this case <code>MessageRepository</code>,
 * operations on others entities are performed using the related services.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
@Service
public class MessageService {

  private final MessageRepository repository;

  private final UserAccountQueryService accountQueryService;

  private final TopicService topicService;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public MessageService(MessageRepository repository, UserAccountQueryService accountQueryService,
      TopicService topicService) {
    this.repository = repository;
    this.accountQueryService = accountQueryService;
    this.topicService = topicService;
  }

  /**
   * Creates a new <code>Message</code> based on the content, topic and author. The message
   * create time will be the moment that the object is created.
   *
   * @param messageContent the <code>String</code> with message's content.
   * @param topicId the <code>Long</code> id of the topic.
   * @param username the <code>String</code> username of the author.
   * @return a new, persisted, message
   */
  public Message createMessage(String messageContent, Long topicId, String username) {

    UserAccount author = accountQueryService.getUserByUsername(username);
    Topic topic = topicService.retrieveTopic(topicId);

    Message message = new Message(messageContent, new Date(), topic, author);
    return repository.save(message);
  }

  /**
   * Retrieves the desired <code>Message</code> having the passed id.
   *
   * @param id the message <code>Long</code> id
   * @return the desired message
   */
  //TODO: throw new exception for not found and remove this null
  public Message retrieveMessage(Long id) {
    return repository.findOne(id);
  }

  /**
   * Updates the desired <code>Message</code> having the passed id
   * with the new content
   *
   * @param id the message <code>Long</code> id
   * @param newContent the new message <code>String</code> content
   * @return the updated desired message
   */
  public Message updateMessage(Long id, String newContent) {
    Message message = this.retrieveMessage(id);

    message.setMessage(newContent);
    message.setModified(Boolean.TRUE);
    message.setUpdateTime(new Date());

    return repository.save(message);
  }

  /**
   * Deletes the desired <code>Message</code> having the passed id
   *
   * @param id the message <code>Long</code> id
   */
  public void deleteMessage(Long id) {
    Message message = this.retrieveMessage(id);
    repository.delete(message);
  }
}
