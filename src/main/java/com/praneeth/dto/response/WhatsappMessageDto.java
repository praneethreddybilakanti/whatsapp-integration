package com.praneeth.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Whatsapp message dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WhatsappMessageDto {
  private String messaging_product;
  private List<Contact> contacts;
  private List<Message> messages;


}
