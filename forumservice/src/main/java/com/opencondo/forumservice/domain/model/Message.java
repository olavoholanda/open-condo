package com.opencondo.forumservice.domain.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * Message is the entity class representing a message in a discussion
 * board, that is, a forum. A message will hold information about its
 * content, time of creation, author and if it was modified or not.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
@Getter
@Setter
@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 100000)
  private String message;

  @Column(nullable = false)
  private Date createTime;

  private Date updateTime;

  @Column(nullable = false)
  private boolean modified;

  @ManyToOne
  private Topic topic;

  @ManyToOne
  private UserAccount author;

  /**
   * Protected constructor, please use the one with parameters, that are required
   * for this object.
   */
  protected Message() {
  }

  /**
   * <code>Message</code> constructor, creates a new message instance with the
   * mandatory parameters.
   *
   * @param message the <code>String</code> holding the message's content
   * @param createTime the <code>Date</code> of creation
   * @param topic the <code>Topic</code> acting as container of this message
   * @param author the <code>UserAccount</code> of the message's author
   */
  public Message(String message, Date createTime, Topic topic, UserAccount author) {
    this.message = message;
    this.createTime = createTime;
    this.modified = Boolean.FALSE;
    this.author = author;
    this.topic = topic;
  }

  @Override
  public String toString() {
    return String.format(
        "Message[id=%d, message='%s', time='%s']",
        id, message, createTime);
  }
}
