package com.praneeth.controller;

import com.praneeth.config.FacebookConfig;
import com.praneeth.model.DartContact;
import com.praneeth.service.WhatsAppWebHookService;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The type Whats app web hook controller.
 */
@RestController
@RequestMapping("social-marketing-communication/whatsapp/v1/webhook")
public class WhatsAppWebHookController {
  private final WhatsAppWebHookService whatsAppWebHookService;
  private final FacebookConfig facebookConfig;

  /**
   * Instantiates a new Whats app web hook controller.
   *
   * @param whatsAppWebHookService
   *     the whats app web hook service
   * @param facebookConfig
   *     the facebook config
   */
  public WhatsAppWebHookController(WhatsAppWebHookService whatsAppWebHookService, FacebookConfig facebookConfig) {
    this.whatsAppWebHookService = whatsAppWebHookService;
    this.facebookConfig = facebookConfig;
  }


  /**
   * Webhook handler response entity.
   *
   * @param requestBody
   *     the request body
   *
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Object> webhookHandler(@RequestBody Map<String, Object> requestBody) {
    // Check the Incoming webhook message

    final Mono<DartContact> whatsAppContact = whatsAppWebHookService.userData(requestBody);
    return ResponseEntity.created(URI.create("/messages/" + Objects.requireNonNull(whatsAppContact.block()).getId())).body(whatsAppContact);

  }


  /**
   * Gets webhook.
   *
   * @param mode
   *     the mode
   * @param token
   *     the token
   * @param challenge
   *     the challenge
   *
   * @return the webhook
   */
  @GetMapping
  public ResponseEntity<String> getWebhook(@RequestParam(name = "hub.mode") String mode,
                                           @RequestParam(name = "hub.verify_token") String token,
                                           @RequestParam(name = "hub.challenge") String challenge) {
    /**
     *
     *This will be the Verify Token value when you set up webhook
     **/
    String verifyToken = facebookConfig.getVerifyToken();
    System.out.println(verifyToken);
    System.out.println(token);
    System.out.println(mode);
    // Check if a token and mode were sent
    if (mode != null && token != null) {
      // Check the mode and token sent are correct
      if (mode.equals("subscribe") && token.equals(verifyToken)) {
        // Respond with 200 OK and challenge token from the request
        System.out.println("WEBHOOK_VERIFIED");
        return ResponseEntity.status(HttpStatus.OK).body(challenge);
      } else {
        // Responds with '403 Forbidden' if verify tokens do not match
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verify token does not match");
      }
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request parameters");
  }


}


