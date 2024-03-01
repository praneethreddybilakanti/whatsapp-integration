package com.praneeth.dto.request.interactive.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Header dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeaderDto {
  private String type;
  private String text;
}