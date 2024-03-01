package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive body.
 */
@Setter
@Getter
public class InteractiveBody {
  private String text;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive body.
   *
   * @param text
   *     the text
   */
  public InteractiveBody(String text) {
    this.text = text;
  }
}