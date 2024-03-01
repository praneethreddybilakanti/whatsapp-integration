package com.praneeth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "uploadedFiles")
@Data
public class UploadFile {
    @Id
    private String id;
    private String fileName;
    private String fileHandle;
    // Add any other necessary fields and getters/setters
}