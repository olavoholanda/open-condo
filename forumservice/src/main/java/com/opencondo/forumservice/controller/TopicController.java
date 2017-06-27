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

  @Autowired
  public TopicController(TopicService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.POST)
  public TopicDTO createTopic(@RequestBody TopicDTO dto) {
    Topic topic = this.service
        .createTopic(dto.getTitle(), dto.getAuthor().getId(), dto.getCondoId());
    return this.parseTopic(topic);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public TopicDTO getTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.retrieveTopic(id);
    return this.parseTopic(topic);
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteTopic(@PathVariable("id") Long id) {
    this.service.deleteTopic(id);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(method = RequestMethod.PUT)
  public TopicDTO updateTopic(@RequestBody TopicDTO dto) {
    Topic topic = this.service.updateTopicTitle(dto.getId(), dto.getTitle());
    return this.parseTopic(topic);
  }

  @RequestMapping(path = "/close/{id}", method = RequestMethod.PUT)
  public TopicDTO closeTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.closeTopic(id);
    return this.parseTopic(topic);
  }

  @RequestMapping(path = "/open/{id}", method = RequestMethod.PUT)
  public TopicDTO openTopic(@PathVariable("id") Long id) {
    Topic topic = this.service.openTopic(id);
    return this.parseTopic(topic);
  }

  private TopicDTO parseTopic(Topic topic) {
    TopicDTO dto = new TopicDTO();
    dto.buildFromEntity(topic);
    return dto;
  }
}
