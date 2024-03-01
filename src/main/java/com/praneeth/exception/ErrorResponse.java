package com.praneeth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Error response.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse {
  private int errorCode;
  private String errorMessage;


}
