package com.praneeth.service;

import com.praneeth.config.FacebookConfig;
import com.praneeth.dao.UploadFileRepository;
import com.praneeth.model.UploadFile;
import java.io.IOException;
import java.util.Objects;
import lombok.Data;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UploadService {
    private final UploadFileRepository uploadFileRepository;
    private final FacebookConfig facebookConfig;
    private final RestTemplate restTemplate;

    public UploadService(UploadFileRepository uploadFileRepository, final FacebookConfig facebookConfig, final RestTemplate restTemplate) {
        this.uploadFileRepository = uploadFileRepository;
        this.facebookConfig = facebookConfig;
        this.restTemplate = restTemplate;
    }

    public Mono<UploadFile> saveFile(MultipartFile file) throws IOException {

        String createSessionUrl = String.format("https://graph.facebook.com/%s/%s/uploads", facebookConfig.getVersion(), facebookConfig.getAppId());
        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.setContentType(MediaType.MULTIPART_FORM_DATA);

      headers.setBearerAuth(facebookConfig.getAccessToken());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
   /*      body.add("file_length", file.getSize());
        body.add("file_type", file.getContentType()); */
       body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<UploadSessionResponse> createSessionResponse = restTemplate.exchange(
            createSessionUrl, HttpMethod.POST, requestEntity, UploadSessionResponse.class);
        /* This code retrieves the ID of the body of a response from a session creation request. */
        UploadSessionResponse uploadSession;
        uploadSession = Objects.requireNonNull(createSessionResponse.getBody());
        String initiateUploadUrl = String.format("https://graph.facebook.com/%s/%s", facebookConfig.getVersion(),
                                                 uploadSession.getId());
        System.out.println(initiateUploadUrl);
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // headers.setBearerAuth(facebookConfig.getAccessToken());
        headers.set("file_offset", String.valueOf("0"));
        headers.set("Authorization", "OAuth " + facebookConfig.getAccessToken());
        HttpEntity<byte[]> initiateUploadRequestEntity = new HttpEntity<>(file.getBytes(), headers);
        System.out.println(initiateUploadRequestEntity.getBody());
        ResponseEntity<UploadResponse> initiateUploadResponse = restTemplate.exchange(
            initiateUploadUrl, HttpMethod.POST, initiateUploadRequestEntity, UploadResponse.class);
        System.out.println(initiateUploadResponse.getStatusCode());

        final UploadResponse uploadResponse = Objects.requireNonNull(initiateUploadResponse.getBody());
        String fileHandle = uploadResponse.getH();
        System.out.println("file handle:" + initiateUploadResponse.getBody());
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setFileHandle(fileHandle);
        return uploadFileRepository.save(uploadFile);
    }


    // DTO class for the Create Session response
    @Data
    private static class UploadSessionResponse {
        private String id;


    }

    // DTO class for the Initiate Upload response
    @Data
    private static class UploadResponse {
        private String h;


    }

    public Flux<UploadFile> getUploadFileFlux() {
        return uploadFileRepository.findAll();
    }

    private ResponseEntity resumeUpload(@PathVariable("uploadSessionId") String uploadSessionId) {
        try {
            String resumeUploadUrl = String.format("https://graph.facebook.com/%s/%s", facebookConfig.getVersion(), uploadSessionId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(facebookConfig.getAccessToken());

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UploadSessionResponse> response = restTemplate.exchange(
                resumeUploadUrl,
                HttpMethod.GET,
                requestEntity,
                UploadSessionResponse.class
                                                                                  );
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
