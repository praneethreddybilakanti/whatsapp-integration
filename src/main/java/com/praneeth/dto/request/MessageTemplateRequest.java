package com.praneeth.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Message template request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MessageTemplateRequest {


  private String name;
  private LanguageRequest language;
  private List<WhatsAppTemplateComponent> components;

  /**
   * The type Whats app template component.
   */
// Add other fields as needed
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WhatsAppTemplateComponent {




    private String name;

    @NotBlank
    private String type;
    private List<WhatsAppTemplateParameter> parameters;
    // Add other fields as needed
  }

  /**
   * The type Whats app template parameter.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WhatsAppTemplateParameter {
    private String type;
    private String text;
    private WhatsAppCurrencyParameter currency;
    private WhatsAppDateTimeParameter dateTime;
    private WhatsAppImage image;
    private String sub_type;
    private String index;
    private List<WhatsAppButtonParameter> parameters;

    // Add other fields as needed
  }

  /**
   * The type Whats app image.
   */
  @Data
  public static class WhatsAppImage {
    private String link;
    // Add other fields as needed
  }

  /**
   * The type Whats app currency parameter.
   */
  @Data
  public static class WhatsAppCurrencyParameter {
    private String fallback_value;
    private String code;
    private int amount_1000;
    // Add other fields as needed
  }

  /**
   * The type Whats app date time parameter.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class WhatsAppDateTimeParameter {
    private String fallback_value;
    // Add other fields as needed
  }

  /**
   * The type Whats app button.
   */
  @Data
  public static class WhatsAppButton {
    private String type;
    private String subType;
    private String index;
    private List<WhatsAppButtonParameter> parameters;
    // Add other fields as needed
  }

  /**
   * The type Whats app button parameter.
   */
  @Data
  public static class WhatsAppButtonParameter {
    private String type;
    private String payload;
    // Add other fields as needed
  }


}
