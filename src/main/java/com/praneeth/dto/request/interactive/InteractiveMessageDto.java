package com.praneeth.dto.request.interactive;

import com.praneeth.dto.request.interactive.list.FooterDto;
import com.praneeth.dto.request.interactive.list.HeaderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Interactive message dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractiveMessageDto {
  private String type;
  private BodyDto body;
  private ActionDto action;
  private HeaderDto header;
  private FooterDto footer;
}


