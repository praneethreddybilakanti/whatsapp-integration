package com.praneeth.controller;

import com.praneeth.dto.request.ContactRequest;
import com.praneeth.dto.response.MessageResponseDto;
import com.praneeth.model.DartMessage;
import com.praneeth.service.MessageService;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * The type Message controller.
 */
@RestController
@RequestMapping("social-marketing-communication/whatsapp/v1/messages")
public class MessageController {
  private final MessageService messageService;
  private final ModelMapper modelMapper;

  /**
   * Instantiates a new Message controller.
   *
   * @param messageService
   *     the message service
   * @param modelMapper
   *     the model mapper
   */
  public MessageController(MessageService messageService, ModelMapper modelMapper) {
    this.messageService = messageService;
    this.modelMapper = modelMapper;
    // Configure ModelMapper to use field matching
    this.modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
  }


  /**
   * Gets message by id.
   *
   * @param id
   *     the id
   *
   * @return the message by id
   */
  @GetMapping("/{id}")
  public DartMessage getMessageById(@PathVariable String id) {
    return messageService.getById(id);

  }

  /**
   * Gets all messages.
   *
   * @return the all messages
   */
  @GetMapping
  public Flux<DartMessage> getAllMessages() {
    return messageService.getAll();
  }


  /**
   * Gets message.
   *
   * @return the message
   */
  @GetMapping(value = "/chats")
  //@Scheduled(cron = "0 */1 * * *")
  public ResponseEntity<Mono<Map<ContactRequest, List<MessageResponseDto>>>> getMessage() {
    return ResponseEntity.ok()
        .body(messageService.groupMessagesByToKey());
  }


}
