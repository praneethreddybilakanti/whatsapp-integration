package com.praneeth.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Fb response.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FbResponse {
  private String id;
  private LocalDateTime localDateTime;
}
