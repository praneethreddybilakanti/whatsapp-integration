package com.praneeth.dto.request.interactive.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Footer dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FooterDto {
  private String text;
}