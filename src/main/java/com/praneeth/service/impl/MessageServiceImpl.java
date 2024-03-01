package com.praneeth.service.impl;

import com.praneeth.config.FacebookConfig;
import com.praneeth.dao.ContactRepository;
import com.praneeth.dao.MessageRepository;
import com.praneeth.dto.request.ContactRequest;
import com.praneeth.dto.request.MessageDto;
import com.praneeth.dto.response.MessageResponseDto;
import com.praneeth.dto.response.WhatsappMessageDto;
import com.praneeth.exception.BadRequestException;
import com.praneeth.exception.ResourceNotFoundException;
import com.praneeth.model.DartMessage;
import com.praneeth.model.template.send.WhatsAppMessage;
import com.praneeth.model.types.Context;
import com.praneeth.service.MessageService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.stream.Collectors.groupingBy;

/**
 * The type Message service.
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

  private final ContactRepository contactRepository;
  private final MessageRepository messageRepository;

  private final FacebookConfig facebookConfig;


  private final ModelMapper modelMapper;

  private final WebClientWrapper webClientWrapper;


  /**
   * Instantiates a new Message service.
   *
   * @param contactRepository
   *     the contact repository
   * @param messageRepository
   *     the message repository
   * @param facebookConfig
   *     the facebook config
   * @param modelMapper
   *     the model mapper
   * @param webClientWrapper
   *     the web client wrapper
   */
  public MessageServiceImpl(final ContactRepository contactRepository, final MessageRepository messageRepository,
                            FacebookConfig facebookConfig,
                            ModelMapper modelMapper,
                            WebClientWrapper webClientWrapper) {
    this.contactRepository = contactRepository;
    this.messageRepository = messageRepository;
    this.facebookConfig = facebookConfig;
    this.modelMapper = modelMapper;
    this.webClientWrapper = webClientWrapper;
  }


  @Override
  public Mono<DartMessage> save(final String contactId, MessageDto messageDTO) {
    log.info("Saving contact : {}", messageDTO);
    return contactRepository.findById(contactId)
        .flatMap(contact -> {
          DartMessage dartMessage = modelMapper.map(messageDTO, DartMessage.class);
          final Mono<WhatsappMessageDto> responseEntityMono =
              webClientWrapper.webClientMessage(dartMessage).doOnError(throwable -> {
                throw new BadRequestException("fb error", throwable);
              });


          final com.praneeth.dto.response.Message whatsAppMessage =
              Objects.requireNonNull(responseEntityMono.block()).getMessages().get(0);
          log.info("Got dkartMessage: " + whatsAppMessage);
          dartMessage.setLocalDateTime(LocalDateTime.now());
          dartMessage.setFrom(facebookConfig.getMyNumberId());
          dartMessage.setSender("dolphins-kart");
          dartMessage.setContactId(contact.getId());
          dartMessage.setContext(new Context(whatsAppMessage.getId()));

          return messageRepository.save(dartMessage);
        });


  }

  @Override
  public DartMessage getById(String id) {
    return messageRepository.findById(id).block();
  }

  @Override
  public Flux<DartMessage> getAll() {
    return messageRepository.findAll();
  }

  @Override
  public Flux<DartMessage> findByContactId(final String contactId) {
    return messageRepository.findByContactId(contactId);
  }

  @Override



  public Mono<Map<ContactRequest, List<MessageResponseDto>>> groupMessagesByToKey() {
    log.info("Group messages by to key");

    Query query = new Query();
    query.limit(10);
    return messageRepository.findAll()

        .map(message -> modelMapper.map(message, MessageResponseDto.class))
        //.buffer()
        .collectList()
        .flatMap(messages -> {
          if (messages.isEmpty()) {
            return Mono.error(new ResourceNotFoundException("No messages found"));
          }

          Map<String, List<MessageResponseDto>> messageMap = messages.stream()
              .filter(messageResponseDto -> {
                String to = messageResponseDto.getTo();
                return to != null && !to.equals(facebookConfig.getMyNumberId());
              })
              .collect(groupingBy(MessageResponseDto::getTo));

          Map<String, List<MessageResponseDto>> fromMap = messages.stream()
              .filter(messageResponseDto -> {
                String from = messageResponseDto.getFrom();
                return from != null && !from.equals(facebookConfig.getMyNumberId());
              })
              .collect(groupingBy(MessageResponseDto::getFrom));

          fromMap.forEach((key, value) -> messageMap.merge(key, value, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
          }));

          log.info(": from maps" + messageMap);

          List<Mono<ContactRequest>> contactMonos = messageMap.keySet().stream()
              .map(key -> contactRepository.findByPhoneNumber(key).log())
              .map(contact -> contact.map(c -> modelMapper.map(c, ContactRequest.class)))
              .collect(Collectors.toList());
      contactMonos.get(0).subscribe(contactRequest1 -> System.out.println(contactRequest1));
          return Mono.zip(contactMonos, results -> {
                Map<ContactRequest, List<MessageResponseDto>> resultMap = new HashMap<>();
                for (int i = 0; i < results.length; i++) {
                  ContactRequest contact = (ContactRequest) results[i];
                  List<MessageResponseDto> messageList = messageMap.getOrDefault(contact.getPhoneNumber(),
                                                                                 new ArrayList<>());
              resultMap.put(contact, messageList);

            }
            System.out.println(resultMap);
            return resultMap;
          });
        });

  }


  @Override
  public Mono<DartMessage> sendTemplate(final String contactId, final WhatsAppMessage message) {
    log.info("Sending template: {} ", message);
    return contactRepository.findById(contactId)
        .flatMap(contact -> {
          DartMessage dartMessage = new DartMessage();
          final Mono<WhatsappMessageDto> responseEntityMono =
              webClientWrapper.webClientSendTemplate(message).doOnError(throwable -> {
                throw new BadRequestException("fb error", throwable);
              });


          final com.praneeth.dto.response.Message whatsAppMessage =
              Objects.requireNonNull(responseEntityMono.block()).getMessages().get(0);
          log.info("Got dkartMessage: " + whatsAppMessage);
          dartMessage.setLocalDateTime(LocalDateTime.now());
          dartMessage.setFrom(facebookConfig.getMyNumberId());
          dartMessage.setSender("dolphins-kart");
          dartMessage.setContactId(contact.getId());
          dartMessage.setContext(new Context(whatsAppMessage.getId()));

          return messageRepository.save(dartMessage);
        });
  }


}