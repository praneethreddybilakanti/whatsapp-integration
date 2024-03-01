package com.praneeth.dao;


import com.praneeth.model.template.create.CustomTemplate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * The interface Custom template repository.
 */
public interface CustomTemplateRepository extends ReactiveMongoRepository<CustomTemplate, String> {
  Mono<Void>  deleteByResponseId(String id);
}
