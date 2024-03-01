package com.praneeth.model.template.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The type Whats app message.
 */
@Data
public class WhatsAppMessage {
  @JsonProperty("messaging_product")
  private String messagingProduct;

  @JsonProperty("recipient_type")
  private String recipientType;

  private String to;
  private String type;

  private Template template;
}
