package com.praneeth.model;

import com.praneeth.model.interactive.InteractiveMessage;
import com.praneeth.model.template.MessageTemplate;
import com.praneeth.model.types.Audio;
import com.praneeth.model.types.Context;
import com.praneeth.model.types.Image;
import com.praneeth.model.types.Reaction;
import com.praneeth.model.types.Text;
import com.praneeth.model.types.Video;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Dkart message.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DartMessage {
  @Id
  private String dkart_message_id;
  @NotBlank
  @Field("messaging_product")
  private String messaging_product;
  @NotBlank
  @Field("recipient_type")
  private String recipient_type;
  @NotBlank
  @Pattern(regexp = "^\\d{12}$")
  private String to;
  private Context context;
  @NotBlank
  private String type;


  private MessageTemplate template;
  private String sender;
  private Text text;

  private Image image;


  private Audio audio;

  private Video video;

  private Reaction reaction;

  private String from;
  private LocalDateTime localDateTime;

  private InteractiveMessage interactive;
  private String contactId;

}
