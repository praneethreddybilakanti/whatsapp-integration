package com.praneeth.exception;

/**
 * The type Resource not found exception.
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Resource not found exception.
   *
   * @param message
   *     the message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Resource not found exception.
   *
   * @param message
   *     the message
   * @param cause
   *     the cause
   */
  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
