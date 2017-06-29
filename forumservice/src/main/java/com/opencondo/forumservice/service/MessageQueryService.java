package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.Message;
import com.opencondo.forumservice.domain.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * The <code>MessageQueryService</code> class is responsible for query methods regarding
 * messages. For CRUD operations look for <code>MessageService</code>.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class MessageQueryService {

  private final MessageRepository repository;

  /**
   * Class constructor with AutoWired dependency injection.
   */
  @Autowired
  public MessageQueryService(MessageRepository repository) {
    this.repository = repository;
  }

  /**
   * Searches a <code>List</code> of messages related to a topic, sorted by creation time.
   * The result is paginated by the page number and size.
   *
   * @param topicId the <code>Long</code> id of the topic, container of the message.
   * @param page the <code>Integer</code> with pagination page.
   * @param size the <code>Integer</code> with pagination size.
   * @return a list of messages, sorted by creation time.
   */
  public List<Message> getLastMessagesByTopicId(Long topicId, Integer page, Integer size) {
    return repository.
        findByTopicId(topicId, PageRequest.of(page, size, Sort.Direction.ASC, "createTime"));
  }
}
