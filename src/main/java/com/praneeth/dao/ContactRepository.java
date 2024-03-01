package com.praneeth.dao;

import com.praneeth.model.DartContact;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The interface Contact repository.
 */
@Repository
public interface ContactRepository extends ReactiveMongoRepository<DartContact, String> {

  /**
   * Find by phone number mono.
   *
   * @param number
   *     the number
   *
   * @return the mono
   */
  Mono<DartContact> findByPhoneNumber(String number);

  /**
   * Find last five messages flux.
   *
   * @param contactId
   *     the contact id
   *
   * @return the flux
   */
  @Query(value = "{ '_id' : ?0 }", fields = "{ 'messages' : { $slice: [0, 2] } }")
  Flux<DartContact> findLastFiveMessages(String contactId);

  /**
   * Find last ten messages flux.
   *
   * @param contactId
   *     the contact id
   *
   * @return the flux
   */
  @Query(value = "{ '_id' : ?0 }", fields = "{ 'messages' : { $slice: [0, 2] } }")
  Flux<DartContact> findLastTenMessages(String contactId);

}
