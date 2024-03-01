package com.praneeth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Contact.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
  private String input;
  @SuppressWarnings("checkstyle:MemberName")
  private String wa_id;
}
