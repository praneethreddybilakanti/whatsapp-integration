package com.praneeth.dto.response;

import com.praneeth.model.DartContact;
import java.util.List;

/**
 * The type Contacts response.
 */
public class ContactsResponse {
  private int duplicateCount;
  private List<DartContact> savedContacts;

  /**
   * Instantiates a new Contacts response.
   *
   * @param duplicateCount
   *     the duplicate count
   * @param savedContacts
   *     the saved contacts
   */
  public ContactsResponse(int duplicateCount, List<DartContact> savedContacts) {
    this.duplicateCount = duplicateCount;
    this.savedContacts = savedContacts;
  }

  /**
   * Gets duplicate count.
   *
   * @return the duplicate count
   */
  public int getDuplicateCount() {
    return duplicateCount;
  }

  /**
   * Sets duplicate count.
   *
   * @param duplicateCount
   *     the duplicate count
   */
  public void setDuplicateCount(int duplicateCount) {
    this.duplicateCount = duplicateCount;
  }

  /**
   * Gets saved contacts.
   *
   * @return the saved contacts
   */
  public List<DartContact> getSavedContacts() {
    return savedContacts;
  }

  /**
   * Sets saved contacts.
   *
   * @param savedContacts
   *     the saved contacts
   */
  public void setSavedContacts(List<DartContact> savedContacts) {
    this.savedContacts = savedContacts;
  }
}
