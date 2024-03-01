package com.praneeth.model.interactive.button;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Button.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Button {
  /**
   * The Type.
   */
  private String type;
  /**
   * The Reply.
   */
  private Reply reply;
}