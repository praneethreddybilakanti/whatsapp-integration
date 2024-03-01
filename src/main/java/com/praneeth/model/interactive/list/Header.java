package com.praneeth.model.interactive.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Header.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header {
  private String type;
  private String text;
}