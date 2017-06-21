package com.opencondo.forumservice.domain.repository;

import com.opencondo.forumservice.domain.model.Message;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The <code>MessageRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional message related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on messages.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

  /**
   * Searches a <code>List</code> of <code>Messages</code> regarding a specific
   * <code>Topic</code>, ordered by a Pageable object
   *
   * @param topicId the long representing the topic id
   * @param pageable the <code>Pageable</code> with pagination and sort information
   * @return a list of messages
   */
  List<Message> findByTopicId(Long topicId, Pageable pageable);
}
