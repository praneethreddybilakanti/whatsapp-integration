package com.praneeth.dto.template;

import lombok.Data;

/**
 * The type Custom template dto.
 */
@Data
public class CustomTemplateResponseDto extends CustomTemplateDto {

  private String id;
  private String status;
  private String responseId;
}
