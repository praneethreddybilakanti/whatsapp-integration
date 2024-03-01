package com.praneeth.dto.interactive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Interactive.
 */
@Setter
@Getter
@NoArgsConstructor
public class Interactive {
  private String messagingProduct;
  private String recipientType;
  private String to;
  private String type;
  private InteractiveObject interactive;

  // getters and setters omitted for brevity

  /**
   * Instantiates a new Interactive.
   *
   * @param messagingProduct
   *     the messaging product
   * @param recipientType
   *     the recipient type
   * @param to
   *     the to
   * @param type
   *     the type
   * @param interactive
   *     the interactive
   */
  public Interactive(String messagingProduct, String recipientType, String to,
                     String type, InteractiveObject interactive) {
    this.messagingProduct = messagingProduct;
    this.recipientType = recipientType;
    this.to = to;
    this.type = type;
    this.interactive = interactive;
  }
}
