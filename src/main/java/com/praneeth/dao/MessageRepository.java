package com.praneeth.dao;

import com.praneeth.model.DartMessage;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * The interface Message repository.
 */
@Repository
public interface MessageRepository extends ReactiveMongoRepository<DartMessage, String> {
  /**
   * Find by contact id flux.
   *
   * @param contactId
   *     the contact id
   *
   * @return the flux
   */
  Flux<DartMessage> findByContactId(String contactId);
}