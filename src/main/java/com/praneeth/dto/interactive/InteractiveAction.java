package com.praneeth.dto.interactive;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive action.
 */
@Setter
@Getter
public class InteractiveAction {
  private String button;
  private List<InteractiveSection> sections;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive action.
   *
   * @param button
   *     the button
   * @param sections
   *     the sections
   */
  public InteractiveAction(String button, List<InteractiveSection> sections) {
    this.button = button;
    this.sections = sections;
  }
}
