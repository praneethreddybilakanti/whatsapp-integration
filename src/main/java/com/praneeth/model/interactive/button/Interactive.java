package com.praneeth.model.interactive.button;

import com.praneeth.model.interactive.Action;
import com.praneeth.model.interactive.Body;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Interactive.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interactive {
  /**
   * The Type.
   */
  public String type;
  /**
   * The Body.
   */
  public Body body;
  /**
   * The Action.
   */
  public Action action;

}