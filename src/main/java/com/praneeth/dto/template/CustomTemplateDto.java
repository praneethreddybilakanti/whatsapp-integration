package com.praneeth.dto.template;

import com.praneeth.shared.TemplateButtonType;
import com.praneeth.shared.TemplateCategory;
import com.praneeth.shared.TemplateFormat;
import com.praneeth.shared.TemplateType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Custom template dto.
 */
@Data
public class CustomTemplateDto {

  private String name;
  private String language;
  private TemplateCategory category;
  private List<ComponentDto> components;

  /**
   * The type Component dto.
   */
  @Data
  public static class ComponentDto {
    private TemplateType type;
    private TemplateFormat format;
    private String text;
    private ExampleDto example;
    private List<ButtonDto> buttons;

  }

  /**
   * The type Example dto.
   */
  @Data
  public static class ExampleDto {
    private List<String> header_text;
    private List<List<String>> body_text;
    private List<String> header_handle;

  }

  /**
   * The type Button dto.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ButtonDto {
    private TemplateButtonType type;
    private String text;
    private String phone_number;
    private String url;
    private String[] example;

  }

}
