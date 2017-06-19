package com.opencondo.forumservice.domain.repository;

import com.opencondo.forumservice.domain.model.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The <code>TopicRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional topic related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on topics.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

  /**
   * Returns a <code>List</code> of <code>Topics</code>, ordered
   * by their creation time. There is no limit here, use it carefully.
   *
   */
  List<Topic> findAllByOrderByCreateTimeDesc();
}
