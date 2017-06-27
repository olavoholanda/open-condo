package com.opencondo.forumservice.controller.dto;

import com.opencondo.forumservice.domain.model.Topic;
import java.util.Date;
import lombok.Getter;

/**
 * Data Transfer Object for the Topic Entity
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
public class TopicDTO implements DTOMapper<Topic> {

  private Long id;
  private String title;
  private Date createTime;
  private boolean open;
  private UserDTO author;
  private String condoId;

  @Override
  public String toString() {
    return String.format(
        "TopicDTO[id=%d, title='%s']",
        id, title);
  }

  /**
   * Fills the DTO with information from the <code>Topic</code> entity.
   *
   * @param topic the <code>Topic</code> entity
   */
  @Override
  public void buildFromEntity(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.createTime = topic.getCreateTime();
    this.open = topic.isOpen();

    this.author = new UserDTO();
    this.condoId = topic.getCondoId();
    this.author.buildFromEntity(topic.getAuthor());
  }
}
