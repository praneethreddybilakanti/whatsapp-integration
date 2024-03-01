package com.praneeth.dto.request.interactive.button;

import lombok.Data;

/**
 * The type Button dto.
 */
@Data
public class ButtonDto {
  private String type;
  private ReplyDto reply;
}