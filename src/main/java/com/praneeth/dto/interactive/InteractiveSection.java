package com.praneeth.dto.interactive;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive section.
 */
@Setter
@Getter
public class InteractiveSection {
  private String title;
  private List<InteractiveRow> rows;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive section.
   *
   * @param title
   *     the title
   * @param rows
   *     the rows
   */
  public InteractiveSection(String title, List<InteractiveRow> rows) {
    this.title = title;
    this.rows = rows;
  }
}