package com.praneeth.dto.response;

import com.praneeth.dto.request.AudioDto;
import com.praneeth.dto.request.ContextDto;
import com.praneeth.dto.request.ImageDto;
import com.praneeth.dto.request.MessageTemplateRequest;
import com.praneeth.dto.request.ReactionDto;
import com.praneeth.dto.request.TextDto;
import com.praneeth.dto.request.VideoDto;
import com.praneeth.dto.request.interactive.InteractiveMessageDto;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Message response dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponseDto {
  private String messaging_product;

  private String recipient_type;


  private String to;
  private ContextDto context;


  @NotBlank
  private String type;

  private TextDto text;
  private ImageDto image;
  private AudioDto audio;
  private VideoDto video;
  private ReactionDto reaction;
  private String from;
  private LocalDateTime localDateTime;
  private MessageTemplateRequest template;
  private InteractiveMessageDto interactive;
  private String sender;

}
