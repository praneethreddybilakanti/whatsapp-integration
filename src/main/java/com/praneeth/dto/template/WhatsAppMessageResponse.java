package com.praneeth.dto.template;

import java.util.List;
import lombok.Data;

/**
 * The type Whats app message response.
 */
@Data
public class WhatsAppMessageResponse {
  private String messagingProduct;
  private List<WhatsAppContact> contacts;
  private List<WhatsAppMessage> messages;

  // Add constructors, getters, and setters as needed


  /**
   * The type Whats app contact.
   */
  @Data
  public static class WhatsAppContact {
    private String input;
    private String waId;

    // Add constructors, getters, and setters as needed
  }

  /**
   * The type Whats app message.
   */
  @Data
  public static class WhatsAppMessage {
    private String id;

    // Add constructors, getters, and setters as needed
  }

  /**
   * The type Whats app image.
   */
  @Data
  public static class WhatsAppImage {
    private String link;

    // Add constructors, getters, and setters as needed
  }

  /**
   * The type Whats app button.
   */
  @Data
  public static class WhatsAppButton {
    private String type;
    private String subType;
    private String index;
    private List<WhatsAppParameter> parameters;

    // Add constructors, getters, and setters as needed
  }

  /**
   * The type Whats app parameter.
   */
  @Data
  public static class WhatsAppParameter {
    private String type;
    private String payload;

    // Add constructors, getters, and setters as needed
  }
}
