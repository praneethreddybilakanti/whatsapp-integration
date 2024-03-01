package com.praneeth.model;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Dkart contact.
 */
@Document(collection = "Contacts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DartContact {
  @Id
  private String id;
  private String name;
  @Pattern(regexp = "^\\d{12}$")
  @Indexed(unique = true)
  private String phoneNumber;

  private String email;
  private List<String> tags;
  private boolean enabled;
  private String customerId;
  /**
   * The Messages.
   */

}
