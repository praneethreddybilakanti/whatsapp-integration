package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive row.
 */
@Setter
@Getter
public class InteractiveRow {
  private String id;
  private String title;
  private String description;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive row.
   *
   * @param id
   *     the id
   * @param title
   *     the title
   * @param description
   *     the description
   */
  public InteractiveRow(String id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }
}