package com.praneeth.controller;

import com.praneeth.dto.request.ContactRequest;
import com.praneeth.dto.request.MessageDto;
import com.praneeth.dto.response.ContactsResponse;
import com.praneeth.model.DartContact;
import com.praneeth.model.DartMessage;
import com.praneeth.model.template.send.WhatsAppMessage;
import com.praneeth.service.MessageService;
import com.praneeth.service.impl.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Contact controller.
 */
@RestController
@RequestMapping("social-marketing-communication/whatsapp/v1/contacts")
public class ContactController {
  private final ContactService contactService;
  private final MessageService messageService;
  private final ModelMapper modelMapper;

  /**
   * Instantiates a new Contact controller.
   *
   * @param contactService
   *     the contact service
   * @param messageService
   *     the message service
   * @param modelMapper
   *     the model mapper
   */
  public ContactController(ContactService contactService, final MessageService messageService,
                           final ModelMapper modelMapper) {
    this.contactService = contactService;
    this.messageService = messageService;
    this.modelMapper = modelMapper;
  }


  /**
   * Create contact mono.
   *
   * @param contactRequest
   *     the contact request
   *
   * @return the mono
   */
  @PostMapping
  public Mono<ResponseEntity<ContactRequest>> createContact(@RequestBody ContactRequest
                                                                contactRequest) {
    return contactService.createContact(modelMapper.map(contactRequest, DartContact.class))
        .map(createdContact -> ResponseEntity.status(HttpStatus.CREATED)
            .body(modelMapper.map(createdContact, ContactRequest.class)));
  }

  /**
   * Gets contact by id.
   *
   * @param id
   *     the id
   *
   * @return the contact by id
   */
  @GetMapping("/{id}")
  public Mono<ResponseEntity<ContactRequest>> getContactById(@PathVariable String id) {
    return contactService.getContactById(id)
        .map(contact -> ResponseEntity.ok(modelMapper.map(contact, ContactRequest.class)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * Gets all contacts.
   *
   * @return the all contacts
   */
  @GetMapping
  public Flux<ContactRequest> getAllContacts() {
    final Flux<DartContact> allContacts = contactService.getAllContacts();
    return allContacts.map(contact -> modelMapper.map(contact, ContactRequest.class));
  }

  /**
   * Update contact mono.
   *
   * @param id
   *     the id
   * @param contactRequest
   *     the dkart contact
   *
   * @return the mono
   */
  @PutMapping("/{id}")
  public Mono<ResponseEntity<ContactRequest>> update(@PathVariable String id,
                                                     @RequestBody ContactRequest contactRequest) {
    contactRequest.setId(id);
    return contactService.updateContact(modelMapper.map(contactRequest, DartContact.class))
        .map(updatedContact -> ResponseEntity.ok(modelMapper.map(updatedContact,
                                                                 ContactRequest.class)))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * Delete contact mono.
   *
   * @param id
   *     the id
   *
   * @return the mono
   */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteContact(@PathVariable String id) {
    return contactService.deleteContact(id)
        .then(Mono.just(ResponseEntity.noContent().build()));
  }

  /**
   * Create message mono.
   *
   * @param contactId
   *     the contact id
   * @param messageDto
   *     the message dto
   *
   * @return the mono
   */
  @PostMapping("/{contactId}/messages")
  public Mono<DartMessage> createMessage(@PathVariable String contactId,
                                         @Valid @RequestBody MessageDto messageDto) {
    return messageService.save(contactId, messageDto);
  }

  /**
   * Send template message mono.
   *
   * @param contactId
   *     the contact id
   * @param message
   *     the message
   *
   * @return the mono
   */
  @PostMapping("/{contactId}/template")
  public Mono<DartMessage> sendTemplateMessage(@PathVariable String contactId,
                                               @RequestBody WhatsAppMessage message) {
    return messageService.sendTemplate(contactId, message);
  }


  /**
   * Gets all messages.
   *
   * @param contactId
   *     the contact id
   *
   * @return the all messages
   */
  @GetMapping("/{contactId}/messages")
  public Flux<DartMessage> getAllMessages(@PathVariable String contactId) {
    return messageService.findByContactId(contactId);
  }

  /**
   * Create contacts mono.
   *
   * @param offset
   *     the offset
   * @param page
   *     the page
   *
   * @return the mono
   */
  @GetMapping("/customers")
  public Mono<ResponseEntity<ContactsResponse>> createContacts(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer offset,
                                                                HttpServletRequest request) {
    final Mono<ContactsResponse> contactFromContactService = contactService.createContactFromContactService(page,
                                                                                                            offset,request);
    return contactFromContactService.map(createdContact -> ResponseEntity.status(HttpStatus.CREATED)
        .body(createdContact));

  }
}
