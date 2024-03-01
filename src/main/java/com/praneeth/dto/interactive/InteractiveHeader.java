package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive header.
 */
@Setter
@Getter
public class InteractiveHeader {
  private String type;
  private String text;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive header.
   *
   * @param type
   *     the type
   * @param text
   *     the text
   */
  public InteractiveHeader(String type, String text) {
    this.type = type;
    this.text = text;
  }
}