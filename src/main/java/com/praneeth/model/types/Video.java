package com.praneeth.model.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Video.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {
  private boolean previewUrl;
  private String body;

}