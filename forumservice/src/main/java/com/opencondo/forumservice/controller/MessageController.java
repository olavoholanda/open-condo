package com.opencondo.forumservice.controller;

import com.opencondo.forumservice.controller.dto.MessageDTO;
import com.opencondo.forumservice.domain.model.Message;
import com.opencondo.forumservice.service.MessageQueryService;
import com.opencondo.forumservice.service.MessageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest endpoint for message resource, including CRUD and query operations.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@RestController
@RequestMapping("/api/message")
public class MessageController {

  private final MessageQueryService queryService;

  private final MessageService service;

  /**
   * Class constructor with AutoWired dependencies injection.
   */
  @Autowired
  public MessageController(MessageQueryService queryService, MessageService service) {
    this.queryService = queryService;
    this.service = service;
  }

  /**
   * Creates a new message.
   *
   * @param topicId the topic id.
   * @param dto a <code>MessageDTO</code> with the message and author.
   * @return the created message.
   */
  @RequestMapping(path = "/{topicId}", method = RequestMethod.POST)
  public MessageDTO createMessage(@PathVariable("topicId") Long topicId,
      @RequestBody MessageDTO dto) {
    Message message = this.service
        .createMessage(dto.getMessage(), topicId, dto.getAuthor().getUsername());
    return this.parseMessage(message);
  }

  /**
   * Retrieves the desired message.
   *
   * @param id the Long message id.
   * @return the desired message.
   */
  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public MessageDTO getMessage(@PathVariable("id") Long id) {
    Message message = this.service.retrieveMessage(id);
    return this.parseMessage(message);
  }

  /**
   * Deletes the desired message.
   *
   * @param id the Long message id.
   * @return 200 if successful.
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
    this.service.deleteMessage(id);
    return ResponseEntity.ok().build();
  }

  /**
   * Updates the desired message.
   *
   * @param dto a <code>MessageDTO</code> with the message id and the new content.
   * @return the message updated.
   */
  @RequestMapping(method = RequestMethod.PUT)
  public MessageDTO updateMessage(@RequestBody MessageDTO dto) {
    Message message = this.service.updateMessage(dto.getId(), dto.getMessage());
    return this.parseMessage(message);
  }

  /**
   * Retrieves all messages ordered by creation time within a topic.
   *
   * @param topicId the topic id, that contains the messages.
   * @return the list of sorted messages.
   */
  @RequestMapping(path = "/topic/{topicId}", method = RequestMethod.GET)
  public List<MessageDTO> getMessagesFromTopic(@PathVariable("topicId") Long topicId,
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "15") Integer size) {
    List<Message> messages = this.queryService.getLastMessagesByTopicId(topicId, page, size);
    List<MessageDTO> dtos = new ArrayList<>();
    messages.forEach(message -> dtos.add(this.parseMessage(message)));
    return dtos;
  }

  /**
   * Parses an entity model Message to a MessageDTO.
   *
   * @param message a <code>Message</code> instance.
   * @return the message data transfer object.
   */
  private MessageDTO parseMessage(Message message) {
    MessageDTO dto = new MessageDTO();
    dto.buildFromEntity(message);
    return dto;
  }
}
