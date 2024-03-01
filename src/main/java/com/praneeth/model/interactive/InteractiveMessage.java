package com.praneeth.model.interactive;

import com.praneeth.model.interactive.list.Footer;
import com.praneeth.model.interactive.list.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Interactive message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractiveMessage {
  private String type;
  private Body body;
  private Action action;
  private Header header;
  private Footer footer;
}


