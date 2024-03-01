package com.praneeth.exception;

/**
 * The type Duplicate email exception.
 */
public class DuplicateEmailException extends RuntimeException {
  /**
   * Instantiates a new Duplicate email exception.
   *
   * @param message
   *     the message
   */
  public DuplicateEmailException(String message) {
    super(message);
  }
}
