package com.praneeth.dao;

import com.praneeth.dto.template.WhatsAppMessageTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Whats app message template repository.
 */
@Repository
public interface WhatsAppMessageTemplateRepository extends ReactiveMongoRepository<WhatsAppMessageTemplate, String> {
}
