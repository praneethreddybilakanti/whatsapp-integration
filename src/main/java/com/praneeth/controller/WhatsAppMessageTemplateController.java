package com.praneeth.controller;

import com.praneeth.dto.template.WhatsAppMessageTemplate;
import com.praneeth.service.WhatsAppMessageTemplateService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * The type Whats app message template controller.
 */
@RestController
@RequestMapping("/whatsapp-templates")
public class WhatsAppMessageTemplateController {
  private final WhatsAppMessageTemplateService templateService;

  /**
   * Instantiates a new Whats app message template controller.
   *
   * @param templateService
   *     the template service
   */
  @Autowired
  public WhatsAppMessageTemplateController(WhatsAppMessageTemplateService templateService) {
    this.templateService = templateService;
  }

  /**
   * Gets all templates.
   *
   * @return the all templates
   */
  @GetMapping
  public Flux<WhatsAppMessageTemplate> getAllTemplates() {
    return templateService.getAllTemplates();
  }

  /**
   * Gets template by id.
   *
   * @param id
   *     the id
   *
   * @return the template by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<WhatsAppMessageTemplate> getTemplateById(@PathVariable("id") String id) {
    Optional<WhatsAppMessageTemplate> template = templateService.getTemplateById(id);
    return template.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  /**
   * Create template response entity.
   *
   * @param template
   *     the template
   *
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<WhatsAppMessageTemplate> createTemplate(@RequestBody WhatsAppMessageTemplate template) {
    WhatsAppMessageTemplate createdTemplate = templateService.createTemplate(template);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTemplate);
  }
}
