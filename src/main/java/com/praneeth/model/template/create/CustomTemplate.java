package com.praneeth.model.template.create;

import com.praneeth.shared.Category;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Custom templates.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "CUSTOM_TEMPLATES")
public class CustomTemplate {
  @Id
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String language;
  @NotBlank
  private Category category;
  /**
   * Templates are composed of various text, media, and interactive components,
   * based on your business needs.
   */
  private List<Component> components = new ArrayList<>();
  /**
   * a text header
   * a text body
   * a footer
   * two quick-reply buttons
   */
private String status;
  private Long responseId;
}