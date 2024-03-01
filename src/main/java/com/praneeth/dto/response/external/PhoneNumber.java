package com.praneeth.dto.response.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * The type Phone number.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

  private String type;

  private String countryCode;
  //removed for oauth
//    @NotBlank(message = "phone Number is missing")
//    @Size(max = 10, min = 10, message = "Wrong phone number")    
  //removed for oauth
  //@MobileNumberNonEmptyValidator(message = "Invalid mobile number")
  @Indexed
  private String number;
}