package com.praneeth.model.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type Reaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
  private String message_id;
  private String emoji;
}