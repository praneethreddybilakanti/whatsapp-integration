package com.praneeth.util;

import com.praneeth.dto.response.external.CustomerBasicInfo;
import com.praneeth.model.DartContact;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Contact utils.
 */
public class ContactUtils {

  /**
   * Transform contact dkart contact.
   *
   */
  private ContactUtils() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Transform contact dkart contact.
   *
   * @param originalContact
   *     the original contact
   *
   * @return the dkart contact
   */
  public static DartContact transformContact(CustomerBasicInfo originalContact) {
    DartContact transformedContact = new DartContact();
    transformedContact.setName(originalContact.getFirstName() + " " + originalContact.getLastName());
    transformedContact.setCustomerId(originalContact.getCustomerId());
    transformedContact.setEmail(originalContact.getEmail());
    if (originalContact.getPhoneDetails() != null && !originalContact.getPhoneDetails().isEmpty()) {
      String countryCode = originalContact.getPhoneDetails().get(0).getCountryCode().replace("+", "");
      String number = originalContact.getPhoneDetails().get(0).getNumber();
      transformedContact.setPhoneNumber(countryCode + number);
    }
    return transformedContact;
  }

  /**
   * Transform contacts list.
   *
   * @param originalContacts
   *     the original contacts
   *
   * @return the list
   */
  public static List<DartContact> transformContacts(List<CustomerBasicInfo> originalContacts) {
    List<DartContact> transformedContacts = new ArrayList<>();
    for (CustomerBasicInfo originalContact : originalContacts) {
      DartContact transformedContact = transformContact(originalContact);
      transformedContacts.add(transformedContact);
    }
    return transformedContacts;
  }
}
