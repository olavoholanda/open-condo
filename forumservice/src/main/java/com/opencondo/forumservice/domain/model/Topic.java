package com.opencondo.forumservice.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/**
 * Topic is the entity class representing a topic in a discussion
 * board, that is, a forum. Generally, a topic is a container of
 * messages with a title for debate. A topic will hold information
 * about its title, author, if it is open and its messages.
 *
 * @author      Olavo Holanda
 * @version     0.1
 * @since       0.1
 */
@Getter
@Setter
@Entity
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private Date createTime;

  @Column(nullable = false)
  private boolean open;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
  private List<Message> messages = new ArrayList<>();

  @Column(nullable = false)
  private UserAccount author;

  protected Topic(){}

  public Topic(String title, Date createTime){
    this.title = title;
    this.createTime = createTime;
    this.open = Boolean.TRUE;
  }

}
