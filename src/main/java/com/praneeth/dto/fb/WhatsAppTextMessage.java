package com.praneeth.dto.fb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Whats app text message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhatsAppTextMessage {
  private String messaging_product;
  private String recipient_type;
  private String to;
  private String type;
  private Text text;

  /**
   * The type Text.
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Text {
    private boolean preview_url;
    private String body;
  }
}
