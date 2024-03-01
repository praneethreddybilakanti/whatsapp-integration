package com.praneeth.model.interactive.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Row.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Row {
  private String id;
  private String title;
  private String description;
}
