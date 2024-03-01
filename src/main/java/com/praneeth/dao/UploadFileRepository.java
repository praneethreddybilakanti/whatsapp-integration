package com.praneeth.dao;

import com.praneeth.model.UploadFile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends ReactiveMongoRepository<UploadFile, String> {
}
