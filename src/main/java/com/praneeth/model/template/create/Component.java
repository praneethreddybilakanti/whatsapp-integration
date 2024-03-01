package com.praneeth.model.template.create;

import com.praneeth.shared.TemplateButtonType;
import com.praneeth.shared.TemplateFormat;
import com.praneeth.shared.TemplateType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Component.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Component {

  private TemplateType type;
  private TemplateFormat format;
  private String text;
  private Example example;
  private List<Button> buttons;
  @SuppressWarnings("checkstyle:MemberName")
  private List<String> header_text; // add this field

  /**
   * The type Example.
   */
  @Data
  public static class Example {
    @SuppressWarnings({"squid:S1197", "checkstyle:MemberName"})
    private String[] header_text;
    @SuppressWarnings({"squid:S1197", "checkstyle:MemberName"})
    private String[][] body_text;
    @SuppressWarnings({"squid:S1197", "checkstyle:MemberName"})
    private String[] header_handle;

  }

  /**
   * The type Button.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Button {
    private TemplateButtonType type;
    private String text;
    @SuppressWarnings("checkstyle:MemberName")
    private String phone_number;
    private String url;
    private String[] example;
  }

}