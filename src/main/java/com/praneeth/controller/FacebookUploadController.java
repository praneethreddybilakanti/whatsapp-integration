package com.praneeth.controller;

import com.praneeth.config.FacebookConfig;
import com.praneeth.model.UploadFile;
import com.praneeth.service.UploadService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Facebook upload controller.
 */
@RestController
@RequestMapping("social-marketing-communication/whatsapp/v1/uploads")
public class FacebookUploadController {
  private final FacebookConfig facebookConfig;
  private final RestTemplate restTemplate;
  private final UploadService uploadService;

  /**
   * Instantiates a new Facebook upload controller.
   *
   * @param facebookConfig
   *     the facebook config
   * @param restTemplate
   *     the rest template
   * @param uploadService
   *     the upload service
   */
  public FacebookUploadController(final FacebookConfig facebookConfig, final RestTemplate restTemplate,
                                  final UploadService uploadService) {
    this.facebookConfig = facebookConfig;
    this.restTemplate = restTemplate;
    this.uploadService = uploadService;
  }

  /**
   * Upload file response entity.
   *
   * @param file
   *     the file
   *
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
    try {
      final Mono<UploadFile> uploadFileMono = uploadService.saveFile(file);
      return ResponseEntity.ok(uploadFileMono.block().getFileHandle());
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
    }
  }

  /**
   * Gets all upload files.
   *
   * @return the all upload files
   */
  @GetMapping
  public Flux<UploadFile> getAllUploadFiles() {
    return uploadService.getUploadFileFlux();
  }

  // DTO class for the Create Session response
  @Data
  private static class UploadSessionResponse {
    private String id;


  }

  // DTO class for the Initiate Upload response
  @Data
  private static class UploadResponse {
    private String fileHandle;


  }
}
