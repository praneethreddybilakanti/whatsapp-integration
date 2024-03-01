package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive footer.
 */
@Setter
@Getter
public class InteractiveFooter {
  private String text;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive footer.
   *
   * @param text
   *     the text
   */
  public InteractiveFooter(String text) {
    this.text = text;
  }
}
