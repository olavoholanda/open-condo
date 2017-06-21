package com.opencondo.forumservice.controller.dto;

import com.opencondo.forumservice.domain.model.Message;
import java.util.Date;
import lombok.Getter;

/**
 * Data Transfer Object for the Message Entity
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
public class MessageDTO implements DTOMapper<Message> {

  private Long id;
  private String message;
  private Date time;
  private boolean modified;
  private UserDTO author;

  @Override
  public String toString() {
    return String.format(
        "MessageDTO[id=%d, message='%s', time='%s', modified='%s']",
        id, message, time, modified);
  }

  /**
   * Fills the DTO with information from the <code>Message</code> entity.
   *
   * @param message the <code>Message</code> entity
   */
  @Override
  public void buildFromEntity(Message message) {
    this.id = message.getId();
    this.message = message.getMessage();
    this.modified = message.isModified();

    if(this.modified){
      this.time = message.getUpdateTime();
    } else {
      this.time = message.getCreateTime();
    }

    this.author = new UserDTO();
    this.author.buildFromEntity(message.getAuthor());
  }
}
