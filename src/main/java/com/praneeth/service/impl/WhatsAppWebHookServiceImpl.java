package com.praneeth.service.impl;

import com.praneeth.config.FacebookConfig;
import com.praneeth.dao.ContactRepository;
import com.praneeth.dao.MessageRepository;
import com.praneeth.dto.request.ContextDto;
import com.praneeth.dto.request.MessageDto;
import com.praneeth.dto.request.MessageTemplateRequest;
import com.praneeth.dto.request.TextDto;
import com.praneeth.dto.response.WhatsappMessageDto;
import com.praneeth.exception.ResourceNotFoundException;
import com.praneeth.model.DartContact;
import com.praneeth.model.DartMessage;
import com.praneeth.service.WhatsAppWebHookService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * The type Whats app web hook service.
 */
@Service
public class WhatsAppWebHookServiceImpl implements WhatsAppWebHookService {
  private final ContactRepository whatsAppContactRepository;
  private final MessageRepository messageRepository;
  private final FacebookConfig facebookConfig;
  private final ModelMapper modelMapper;
  private final WebClientWrapper webClient;

  /**
   * Instantiates a new Whats app web hook service.
   *
   * @param whatsAppContactRepository
   *     the whats app contact repository
   * @param messageRepository
   *     the message repository
   * @param facebookConfig
   *     the facebook config
   * @param modelMapper
   *     the model mapper
   * @param webClient
   *     the web client
   */
  public WhatsAppWebHookServiceImpl(
      ContactRepository whatsAppContactRepository,
      final MessageRepository messageRepository, FacebookConfig facebookConfig,
      ModelMapper modelMapper, WebClientWrapper webClient) {
    this.whatsAppContactRepository = whatsAppContactRepository;
    this.messageRepository = messageRepository;
    this.facebookConfig = facebookConfig;
    this.modelMapper = modelMapper;
    this.webClient = webClient;

  }

  @Override
  public Mono<DartContact> userData(Map<String, Object> requestBody) {

    // info on WhatsApp text message payload:
    // https://developers.facebook.com/docs/whatsapp/cloud-api/webhooks/payload-examples#text-messages
    if (requestBody.containsKey("object") &&
        requestBody.get("object").equals("whatsapp_business_account")) {

      List<Map<String, Object>> entryList =
          (List<Map<String, Object>>) requestBody.get("entry");
      Map<String, Object> entry = entryList.get(0);
      List<Map<String, Object>> changes =
          (List<Map<String, Object>>) entry.get("changes");
      Map<String, Object> change = changes.get(0);
      Map<String, Object> value =
          (Map<String, Object>) change.get("value");
      List<Map<String, Object>> messages =
          (List<Map<String, Object>>) value.get("messages");

      if (messages != null && !messages.isEmpty()) {
        Map<String, Object> message = messages.get(0);
        String from = (String) message.get("from");
        String phoneNumberId =
            (String) value.get("metadata.phone_number_id");
        String msgBody = (String) message.get("text.body");
        String context_id = (String) message.get("id");
        MessageDto text = GetTextUser(message);
        text.setFrom(from);
        text.setContext(new ContextDto(context_id));
        final DartMessage receiverDartMessage =
            modelMapper.map(text, DartMessage.class);
        receiverDartMessage.setLocalDateTime(LocalDateTime.now());

        receiverDartMessage.setSender("customer");

        return saveData(receiverDartMessage);
      }


    }
    throw new RuntimeException("something went wrong");
  }

  @Override
  public WhatsappMessageDto sendHelloWorldTemplate(MessageTemplateRequest request) {
    return null;
  }

  private Mono<DartContact> saveData(DartMessage dartMessage) {
    final DartContact dartContact =
        whatsAppContactRepository.findByPhoneNumber(dartMessage.getFrom()).block();
    if (dartContact == null) {
      throw new ResourceNotFoundException("contact not found");
    } else {
      dartMessage.setContactId(dartContact.getId());
      messageRepository.save(dartMessage);

    }
    return whatsAppContactRepository.save(dartContact);
  }


  private MessageDto GetTextUser(Map<String, Object> messages) {
    String text = "";
    MessageDto messageDto = new MessageDto();
    messageDto.setTo(facebookConfig.getMyNumberId());
    messageDto.setMessaging_product("whatsapp");
    messageDto.setRecipient_type("individual");
    switch ((String) messages.get("type")) {
      case "text":
        text = (String) ((Map<String, Object>) messages.get(
            "text")).get("body");
        messageDto.setType("text");
        TextDto text1 = new TextDto(true, text);
        messageDto.setText(text1);
        break;
      case "interactive":
        Map<String, Object> interactiveObject =
            (Map<String, Object>) messages.get("interactive");

        switch ((String) interactiveObject.get("type")) {
          case "button_reply":
            text =
                (String) ((Map<String, Object>) interactiveObject.get(
                    "button_reply")).get("title");
            messageDto.setType("text");
            TextDto text2 = new TextDto(true, text);
            messageDto.setText(text2);
            break;
          case "list_reply":
            text =
                (String) ((Map<String, Object>) interactiveObject.get(
                    "list_reply")).get("title");
            messageDto.setType("text");
            TextDto text3 = new TextDto(true, text);
            messageDto.setText(text3);
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }
    return messageDto;
  }
}