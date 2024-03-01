package com.praneeth.service;

import com.praneeth.dto.request.ContactRequest;
import com.praneeth.dto.request.MessageDto;
import com.praneeth.dto.response.MessageResponseDto;
import com.praneeth.model.DartMessage;
import com.praneeth.model.template.send.WhatsAppMessage;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Message service.
 */
public interface MessageService {
  /**
   * Save mono.
   *
   * @param contactId
   *     the contact id
   * @param messageDTO
   *     the message dto
   *
   * @return the mono
   */
  Mono<DartMessage> save(String contactId, MessageDto messageDTO);

  /**
   * Gets by id.
   *
   * @param id
   *     the id
   *
   * @return the by id
   */
  DartMessage getById(String id);

  /**
   * Gets all.
   *
   * @return the all
   */
  Flux<DartMessage> getAll();

  /**
   * Find by contact id flux.
   *
   * @param contactId
   *     the contact id
   *
   * @return the flux
   */
  Flux<DartMessage> findByContactId(String contactId);

  /**
   * Group messages by to key mono.
   *
   * @return the mono
   */
  Mono<Map<ContactRequest, List<MessageResponseDto>>> groupMessagesByToKey();

  /**
   * Send template mono.
   *
   * @param contactId
   *     the contact id
   * @param message
   *     the message
   *
   * @return the mono
   */
  Mono<DartMessage> sendTemplate(String contactId, WhatsAppMessage message);

}