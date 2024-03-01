package com.praneeth.dto.request.interactive.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Row dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RowDto {
  private String id;
  private String title;
  private String description;
}
