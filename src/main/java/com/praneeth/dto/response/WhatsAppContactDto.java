package com.praneeth.dto.response;

import java.util.List;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * The type Whats app contact dto.
 */
@Data
public class WhatsAppContactDto
    extends RepresentationModel<WhatsAppContactDto> {
  private String id;
  private String name;
  private String phone_number;
  private List<MessageResponseDto> messages;
}
