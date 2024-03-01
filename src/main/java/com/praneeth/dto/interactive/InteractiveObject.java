package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Interactive object.
 */
@Setter
@Getter
public class InteractiveObject {
  private String type;
  private InteractiveHeader header;
  private InteractiveBody body;
  private InteractiveFooter footer;
  private InteractiveAction action;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive object.
   *
   * @param type
   *     the type
   * @param header
   *     the header
   * @param body
   *     the body
   * @param footer
   *     the footer
   * @param action
   *     the action
   */
  public InteractiveObject(String type, InteractiveHeader header,
                           InteractiveBody body, InteractiveFooter footer,
                           InteractiveAction action) {
    this.type = type;
    this.header = header;
    this.body = body;
    this.footer = footer;
    this.action = action;
  }
}
