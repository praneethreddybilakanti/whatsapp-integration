package com.praneeth.service.impl;

import com.praneeth.config.FacebookConfig;
import com.praneeth.dto.response.TemplateReturnResponseDto;
import com.praneeth.dto.response.WhatsappMessageDto;
import com.praneeth.dto.template.CustomTemplateDto;
import com.praneeth.dto.template.DeleteResponse;
import com.praneeth.model.DartMessage;
import com.praneeth.model.template.send.WhatsAppMessage;
import java.time.Duration;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * The type Web client wrapper.
 */
@Service
@Slf4j
public class WebClientWrapper {
  private final FacebookConfig facebookConfig;
  private final WebClient webClient;
  private final RestTemplate restTemplate;

  /**
   * Instantiates a new Web client wrapper.
   *
   * @param facebookConfig
   *     the facebook config
   * @param webClient
   *     the web client
   * @param restTemplate
   *     the rest template
   */
  public WebClientWrapper(final FacebookConfig facebookConfig,
                          final WebClient.Builder webClient, final RestTemplate restTemplate) {
    this.facebookConfig = facebookConfig;
    this.webClient = webClient
        .baseUrl(urlBuilder())
        .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(Duration.ofSeconds(10))))
        .build();
    this.restTemplate = restTemplate;
  }


  /**
   * Web client message mono.
   *
   * @param request
   *     the request
   *
   * @return the mono
   */
  protected Mono<WhatsappMessageDto> webClientMessage(final DartMessage request) {
    System.out.println(request);

    return webClient.post()
        .header("Authorization",
                "Bearer " + facebookConfig.getAccessToken())
        .contentType(APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(WhatsappMessageDto.class);

  }

  /**
   * Web client send template mono.
   *
   * @param request
   *     the request
   *
   * @return the mono
   */
  protected Mono<WhatsappMessageDto> webClientSendTemplate(final WhatsAppMessage request) {
    System.out.println(request);

    return webClient.post()
        .header("Authorization",
                "Bearer " + facebookConfig.getAccessToken())
        .contentType(APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(WhatsappMessageDto.class);

  }

  /**
   * Web client for custom template mono.
   *
   * @param request
   *     the request
   *
   * @return the mono
   */
  protected Mono<TemplateReturnResponseDto> customTemplate(final CustomTemplateDto request) {
    System.out.println(request);
    return webClient.post()
        .uri(urlBuilderForTemplates())
        .header("Authorization",
                "Bearer " + facebookConfig.getAccessToken())
        .contentType(APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(TemplateReturnResponseDto.class);


  }

  private String urlBuilder() {
    return facebookConfig.getApiVersion() + "/" + facebookConfig.getMyNumberId() + "/messages";

  }

  private String urlBuilderForTemplates() {
    return facebookConfig.getApiVersion() + "/" + facebookConfig.getWabaId() + "/message_templates";

  }

  /**
   * Gets custom templates.
   *
   * @param fields
   *     the fields
   * @param limit
   *     the limit
   *
   * @return the custom templates
   */
  public ResponseEntity<Map<String, Object>> getCustomTemplates(final String fields, final int limit) {

    String url = String.format(urlBuilderForTemplates()+"?fields=%s&limit=%d", fields, limit);
    log.info(url);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.setBearerAuth(facebookConfig.getAccessToken());

    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();


    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);





    return restTemplate.exchange(
        url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Map<String, Object>>() {});
  }

  /**
   * Delete template response entity.
   *
   * @param name
   *     the name
   * @param hsmId
   *     the hsm id
   *
   * @return the response entity
   */
  public ResponseEntity<DeleteResponse> deleteTemplate(final String name, final String hsmId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.setBearerAuth(facebookConfig.getAccessToken());
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlBuilderForTemplates());

    if (name != null) {
      builder.queryParam("name", name);
    }
    if (hsmId != null) {
      builder.queryParam("hsm_id", hsmId);
    }

    HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
    System.out.printf(builder.toUriString());
    ResponseEntity<DeleteResponse> response = restTemplate.exchange(
        builder.build().toUri(), HttpMethod.DELETE, requestEntity, DeleteResponse.class);
log.info("Delete response: {}", response);
    return ResponseEntity.ok(response.getBody());
  }
}


