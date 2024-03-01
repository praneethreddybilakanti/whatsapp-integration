package com.praneeth.model.interactive.list;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Section.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {
  private String title;
  private List<Row> rows;

}