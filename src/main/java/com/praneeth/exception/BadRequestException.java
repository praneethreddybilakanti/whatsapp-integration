package com.praneeth.exception;

/**
 * The type Bad request exception.
 */
public class BadRequestException extends RuntimeException {

  /**
   * Instantiates a new Bad request exception.
   *
   * @param message
   *     the message
   */
  public BadRequestException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Bad request exception.
   *
   * @param message
   *     the message
   * @param cause
   *     the cause
   */
  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
