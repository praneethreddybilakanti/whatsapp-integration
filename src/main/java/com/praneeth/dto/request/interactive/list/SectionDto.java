package com.praneeth.dto.request.interactive.list;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Section dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto {
  private String title;
  private List<RowDto> rows;

}