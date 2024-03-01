package com.praneeth.dto.request;

import com.praneeth.dto.request.interactive.InteractiveMessageDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Message dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MessageDto {
  @NotBlank
  private String messaging_product;

  @NotBlank
  private String recipient_type;

  @NotBlank
  @Pattern(regexp = "^\\d{12}$")
  private String to;
  private ContextDto context;

  private String name;

  @NotBlank
  private String type;
  private MessageTemplateRequest template;
  private TextDto text;
  private ImageDto image;
  private AudioDto audio;
  private VideoDto video;
  private ReactionDto reaction;

  private String sender;
  private String from;
  private InteractiveMessageDto interactive;

}
