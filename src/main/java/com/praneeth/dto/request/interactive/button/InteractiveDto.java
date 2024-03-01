package com.praneeth.dto.request.interactive.button;

import com.praneeth.dto.request.interactive.ActionDto;
import com.praneeth.dto.request.interactive.BodyDto;
import lombok.Data;


/**
 * The type Interactive dto.
 */
@Data
public class InteractiveDto {
  private String type;
  private BodyDto body;
  private ActionDto action;


}
