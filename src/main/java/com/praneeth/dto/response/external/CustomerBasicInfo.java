package com.praneeth.dto.response.external;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Customer basic info.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBasicInfo {

  private String firstName;
  private String lastName;
  private String customerId;
  private String email;
  private List<PhoneNumber> phoneDetails;
  private Boolean isRegistered;
}