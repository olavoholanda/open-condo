package com.opencondo.forumservice.controller;

import com.opencondo.forumservice.controller.dto.TopicDTO;
import com.opencondo.forumservice.domain.model.Topic;
import com.opencondo.forumservice.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest endpoint for topic resource, including CRUD and query operations.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController {

  private final TopicService service;

  /**
   * Class constructor with AutoWired dependency injection.
   */
  @Autowired
  public TopicController(TopicService service) {
    this.service = service;
  }

  /**
   * Creates a new topic.
   *
   * @param dto a <code>TopicDTO</code> with the topic title, author and condo id.
   * @return the created topic.
   */
  @RequestMapping(method = RequestMethod.POST)
  public TopicDTO createTopic(@RequestBody TopicDTO dto) {
    Topic topic = this.service
        .createTopic(dto.getTitle(), dto.getAuthor().getId(), dto.getCondoId());
    return this.parseTopic(topic);
  }

  /**
   * Retrieves the desired topic.
   *
   * @param id the Long topic id.
   * @return the desired topic.
   */
  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public TopicDTO getTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.retrieveTopic(id);
    return this.parseTopic(topic);
  }

  /**
   * Deletes the desired topic.
   *
   * @param id the Long topic id.
   * @return 200 if successful.
   */
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteTopic(@PathVariable("id") Long id) {
    this.service.deleteTopic(id);
    return ResponseEntity.ok().build();
  }

  /**
   * Updates the desired topic.
   *
   * @param dto a <code>TopicDTO</code> with the topic id and the new title.
   * @return the topic updated.
   */
  @RequestMapping(method = RequestMethod.PUT)
  public TopicDTO updateTopic(@RequestBody TopicDTO dto) {
    Topic topic = this.service.updateTopicTitle(dto.getId(), dto.getTitle());
    return this.parseTopic(topic);
  }

  /**
   * Closes a specific topic.
   *
   * @param id the Long topic id.
   * @return the topic updated.
   */
  @RequestMapping(path = "/close/{id}", method = RequestMethod.PUT)
  public TopicDTO closeTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.closeTopic(id);
    return this.parseTopic(topic);
  }

  /**
   * Opens a specific topic.
   *
   * @param id the Long topic id.
   * @return the topic updated.
   */
  @RequestMapping(path = "/open/{id}", method = RequestMethod.PUT)
  public TopicDTO openTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.openTopic(id);
    return this.parseTopic(topic);
  }

  /**
   * Parses an entity model Topic to a TopicDTO.
   *
   * @param topic a <code>Topic</code> instance.
   * @return the topic data transfer object.
   */
  private TopicDTO parseTopic(Topic topic) {
    TopicDTO dto = new TopicDTO();
    dto.buildFromEntity(topic);
    return dto;
  }
}
