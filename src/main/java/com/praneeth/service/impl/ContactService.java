package com.praneeth.service.impl;

import com.praneeth.dao.ContactRepository;
import com.praneeth.dto.response.ContactsResponse;
import com.praneeth.dto.response.external.CustomerBasicInfo;
import com.praneeth.exception.DuplicateEmailException;
import com.praneeth.feign.CustomerFeignClient;
import com.praneeth.model.DartContact;
import com.praneeth.util.ContactUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Contact service.
 */
@Service
@Slf4j
public class ContactService {
  private final ContactRepository contactRepository;
  private final CustomerFeignClient customerFeignClient;
  private final ModelMapper modelMapper;
  private final RestTemplate restTemplate;

  /**
   * Instantiates a new Contact service.
   *
   * @param contactRepository
   *     the contact repository
   * @param customerFeignClient
   *     the customer feign client
   * @param modelMapper
   *     the model mapper
   * @param restTemplate
   */
  public ContactService(ContactRepository contactRepository,
                       final CustomerFeignClient customerFeignClient,
                        final ModelMapper modelMapper, final RestTemplate restTemplate) {
    this.contactRepository = contactRepository;
    this.customerFeignClient = customerFeignClient;
    this.modelMapper = modelMapper;
    this.restTemplate = restTemplate;
  }
  @Value("${customer-service}")
  private String baseUrl;
  /**
   * Create contact mono.
   *
   * @param contact
   *     the contact
   *
   * @return the mono
   */
  public Mono<DartContact> createContact(DartContact contact) {
    return validatePhoneNumberUniqueness(contact.getPhoneNumber())
        .flatMap(valid -> {
          if (Boolean.FALSE.equals(valid)) {
            throw new DuplicateEmailException("Phone number must be unique.");
          } else {
            return contactRepository.save(contact);
          }
        });
  }

  /**
   * Gets contact by id.
   *
   * @param id
   *     the id
   *
   * @return the contact by id
   */
  public Mono<DartContact> getContactById(String id) {
    return contactRepository.findById(id);
  }

  /**
   * Gets all contacts.
   *
   * @return the all contacts
   */
  public Flux<DartContact> getAllContacts() {
    return contactRepository.findAll();
  }

  /**
   * Update contact mono.
   *
   * @param dartContact
   *     the dkart contact
   *
   * @return the mono
   */
  public Mono<DartContact> updateContact(DartContact dartContact) {
    return contactRepository.save(dartContact);
  }

  /**
   * Delete contact mono.
   *
   * @param id
   *     the id
   *
   * @return the mono
   */
  public Mono<Void> deleteContact(String id) {
    return contactRepository.deleteById(id);
  }


  private Mono<Boolean> validatePhoneNumberUniqueness(String phoneNumber) {
    return contactRepository.findByPhoneNumber(phoneNumber)
        .hasElement()
        .map(exists -> !exists);
  }

  /**
   * Create contact from contact service mono.
   *
   * @param page
   *     the page
   * @param offset
   *     the offset
   *
   * @return the mono
   */
  @SuppressWarnings("checkstyle:OperatorWrap")
  public Mono<ContactsResponse> createContactFromContactService(Integer page, Integer offset,HttpServletRequest request) {
    final List<CustomerBasicInfo> customers = makeGetRequest(page, offset,request);
    //final List<CustomerBasicInfo> customers = customerFeignClient.getBasicCustomerDetails(page, offset).getBody();
    assert customers != null;
    final List<DartContact> contacts = ContactUtils.transformContacts(customers);

    log.info("Create contact from contact service,{}", contacts);
    return Flux.fromIterable(contacts)
        .flatMap(contact ->
                     Objects.requireNonNull(validatePhoneNumberUniqueness(contact.getPhoneNumber()).log()
                                                .flatMap(valid -> {
                                                  if (valid.equals(Boolean.TRUE)) {
                                                    return Mono.just(contactRepository.save(modelMapper.map(contact,
                                                                                                            DartContact.class))).log();
                                                  } else {
                                                    return Mono.error(new DuplicateEmailException("Phone number must " +
                                                                                                      "be unique."));
                                                  }
                                                }).block()).log()
                )
        .collectList()
        .flatMap(savedContacts -> {
          int duplicateCount = contacts.size() - savedContacts.size();
          ContactsResponse response = new ContactsResponse(duplicateCount, savedContacts);
          return Mono.just(response);
        });
  }
  public List<CustomerBasicInfo> makeGetRequest(Integer page, Integer offset, HttpServletRequest request) {
    String url = baseUrl + "/customer/basic-info?page=" + page + "&offset=" + offset;
    System.out.println(url);
    HttpHeaders headers = new HttpHeaders();
    String ipAddress = request.getHeader("X-Forwarded-For");

    if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
    }
    System.out.println(ipAddress);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    ResponseEntity<CustomerBasicInfo[]> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        CustomerBasicInfo[].class
                                                           );
    System.out.println(response);
    // Handle the response here
    if (response.getStatusCode().is2xxSuccessful()) {
      CustomerBasicInfo[] responseBody = response.getBody();
      return Arrays.asList(responseBody);
    } else {
      System.out.println("Request failed with status code: " + response.getStatusCodeValue());
      return Collections.emptyList();
    }
  }
}