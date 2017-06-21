package com.opencondo.forumservice.controller.dto;

/**
 * Simple mapper interface for DTOs. Every DTO must implement this interface.
 * This is a contract interface, where each DTO must override the <code>buildFromEntity</code>
 * method. So, each DTO is responsible for mapping to its <code>E</code> entity.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public interface DTOMapper<E> {

  /**
   * Fills the DTO with information from the entity.
   *
   * @param entity the <code>E</code> generics entity
   */
  void buildFromEntity(E entity);
}
