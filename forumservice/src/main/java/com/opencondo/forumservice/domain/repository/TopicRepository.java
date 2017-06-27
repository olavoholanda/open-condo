package com.opencondo.forumservice.domain.repository;

import com.opencondo.forumservice.domain.model.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

/**
 * The <code>TopicRepository</code> interface extends Spring <code>JpaRepository</code
 * providing useful additional topic related queries on the database. This is interface
 * should be used in the service layer for create, retrieve, update and delete operations
 * on topics.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {

  /**
   * Searches a <code>List</code> of <code>Topics</code> regarding a specific
   * condominium id, ordered by a Pageable object
   *
   * @param condoId the String representing the condominium id
   * @param pageable the <code>Pageable</code> with pagination and sort information
   * @return a list of topics
   */
  List<Topic> findByCondoId(String condoId, Pageable pageable);
}
