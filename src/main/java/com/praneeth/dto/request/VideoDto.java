package com.praneeth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Video dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class VideoDto {
  private boolean previewUrl;
  private String body;

  // constructors, getters, setters
}
