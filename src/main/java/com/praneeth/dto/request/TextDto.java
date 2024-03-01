package com.praneeth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Text dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TextDto {
  private boolean previewUrl;
  private String body;

}
