package com.praneeth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Reaction dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ReactionDto {
  private String message_id;
  private String emoji;
  // constructors, getters, setters
}
