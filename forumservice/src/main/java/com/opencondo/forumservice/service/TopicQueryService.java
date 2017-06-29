package com.opencondo.forumservice.service;

import com.opencondo.forumservice.domain.model.Topic;
import com.opencondo.forumservice.domain.repository.TopicRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * The <code>TopicQueryService</code> class is responsible for query methods regarding
 * topics. For CRUD operations look for <code>TopicService</code>.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Service
public class TopicQueryService {

  private final TopicRepository repository;

  /**
   * Class constructor with AutoWired dependency injection.
   */
  @Autowired
  public TopicQueryService(TopicRepository repository) {
    this.repository = repository;
  }

  /**
   * Searches a <code>List</code> of topics related to a condominium, sorted by creation time.
   * The result is paginated by the page number and size.
   *
   * @param condoId the <code>String</code> id of the condominium.
   * @param page the <code>Integer</code> with pagination page.
   * @param size the <code>Integer</code> with pagination size.
   * @return a list of topics, sorted by creation time.
   */
  public List<Topic> getLastTopicsByCondoId(String condoId, Integer page, Integer size) {
    return repository.
        findByCondoId(condoId, PageRequest.of(page, size, Sort.Direction.ASC, "createTime"));
  }
}
