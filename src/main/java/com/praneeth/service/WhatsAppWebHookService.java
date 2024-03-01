package com.praneeth.service;

import com.praneeth.dto.request.MessageTemplateRequest;
import com.praneeth.dto.response.WhatsappMessageDto;
import com.praneeth.model.DartContact;
import java.util.Map;
import reactor.core.publisher.Mono;

/**
 * The interface Whats app web hook service.
 */
public interface WhatsAppWebHookService {
  /**
   * User data mono.
   *
   * @param requestBody
   *     the request body
   *
   * @return the mono
   */
  Mono<DartContact> userData(Map<String, Object> requestBody);

  /**
   * Send hello world template whatsapp message dto.
   *
   * @param request
   *     the request
   *
   * @return the whatsapp message dto
   */
  WhatsappMessageDto sendHelloWorldTemplate(MessageTemplateRequest request);
}
