package com.opencondo.accountservice.service.exception;

/**
 * A custom exception indicating an entity was not found in the repository.
 *
 * @author Olavo Holanda
 * @version 0.1
 * @since 0.1
 */
public class EntityNotFoundException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the {@link
   * #getMessage()} method.
   */
  public EntityNotFoundException(String message) {
    super(message);
  }
}
