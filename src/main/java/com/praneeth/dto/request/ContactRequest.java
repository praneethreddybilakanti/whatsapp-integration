package com.praneeth.dto.request;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Contact request.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ContactRequest {
  private String id;
  private String name;
  @Pattern(regexp = "^\\d{12}$")
  private String phoneNumber;

  private String email;
  private List<String> tags;
  private boolean enabled;


}
