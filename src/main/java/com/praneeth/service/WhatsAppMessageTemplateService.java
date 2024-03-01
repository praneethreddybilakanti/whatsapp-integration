package com.praneeth.service;

import com.praneeth.dao.WhatsAppMessageTemplateRepository;
import com.praneeth.dto.template.WhatsAppMessageTemplate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * The type Whats app message template service.
 */
@Service
public class WhatsAppMessageTemplateService {
  private final WhatsAppMessageTemplateRepository repository;

  /**
   * Instantiates a new Whats app message template service.
   *
   * @param repository
   *     the repository
   */
  @Autowired
  public WhatsAppMessageTemplateService(WhatsAppMessageTemplateRepository repository) {
    this.repository = repository;
  }

  /**
   * Gets all templates.
   *
   * @return the all templates
   */
  public Flux<WhatsAppMessageTemplate> getAllTemplates() {
    return repository.findAll();
  }

  /**
   * Gets template by id.
   *
   * @param id
   *     the id
   *
   * @return the template by id
   */
  public Optional<WhatsAppMessageTemplate> getTemplateById(String id) {
    return repository.findById(id).blockOptional();
  }

  /**
   * Create template whats app message template.
   *
   * @param template
   *     the template
   *
   * @return the whats app message template
   */
  public WhatsAppMessageTemplate createTemplate(WhatsAppMessageTemplate template) {
    return repository.save(template).block();
  }

  /**
   * Update template whats app message template.
   *
   * @param id
   *     the id
   * @param updatedTemplate
   *     the updated template
   *
   * @return the whats app message template
   */
  public WhatsAppMessageTemplate updateTemplate(String id, WhatsAppMessageTemplate updatedTemplate) {
    Optional<WhatsAppMessageTemplate> existingTemplate = repository.findById(id).blockOptional();
    if (existingTemplate.isPresent()) {
      WhatsAppMessageTemplate template = existingTemplate.get();
      template.setName(updatedTemplate.getName());
      template.setLanguageCode(updatedTemplate.getLanguageCode());
      // Update other fields as needed
      return repository.save(template).block();
    }
    return null;
  }

  /**
   * Delete template boolean.
   *
   * @param id
   *     the id
   *
   * @return the boolean
   */
  public boolean deleteTemplate(String id) {
    Optional<WhatsAppMessageTemplate> existingTemplate = repository.findById(id).blockOptional();
    if (existingTemplate.isPresent()) {
      repository.deleteById(id);
      return true;
    }
    return false;
  }
}
