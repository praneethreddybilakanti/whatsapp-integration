package com.praneeth.model.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type Text.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Text {
  private boolean preview_url;
  private String body;


}
