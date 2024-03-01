package com.praneeth.service.impl;

import com.praneeth.dao.CustomTemplateRepository;
import com.praneeth.dto.response.TemplateResponseDto;
import com.praneeth.dto.response.TemplateReturnResponseDto;
import com.praneeth.dto.template.CustomTemplateDto;
import com.praneeth.dto.template.CustomTemplateResponseDto;
import com.praneeth.dto.template.DeleteResponse;
import com.praneeth.model.template.create.CustomTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Custom template service.
 */
@Service
public class CustomTemplateServiceImpl {

  private final CustomTemplateRepository customTemplateRepository;

  private final ModelMapper modelMapper;
  private final WebClientWrapper webClientWrapper;

  /**
   * Instantiates a new Custom template service.
   *
   * @param customTemplateRepository
   *     the custom template repository
   * @param modelMapper
   *     the model mapper
   * @param webClientWrapper
   *     the web client wrapper
   */
  public CustomTemplateServiceImpl(final CustomTemplateRepository customTemplateRepository, ModelMapper modelMapper,
                                   final WebClientWrapper webClientWrapper) {
    this.customTemplateRepository = customTemplateRepository;
    this.modelMapper = modelMapper;
    this.webClientWrapper = webClientWrapper;
  }

  /**
   * Create mono.
   *
   * @param customTemplateDto
   *     the custom template dto
   *
   * @return the mono
   */
  public Mono<CustomTemplateResponseDto> create(CustomTemplateDto customTemplateDto) {
    TemplateReturnResponseDto objectMono = webClientWrapper.customTemplate(customTemplateDto).block();
    System.out.printf("converting template " + objectMono);
    final CustomTemplate customTemplate = convertToEntity(customTemplateDto);
    customTemplate.setStatus(objectMono.getStatus());
    customTemplate.setResponseId(objectMono.getId());
   return customTemplateRepository.save(customTemplate).map(this::convertResponseToDto);
  }

  /**
   * Update mono.
   *
   * @param customTemplateDto
   *     the custom template dto
   * @param id
   *     the id
   *
   * @return the mono
   */
  public Mono<CustomTemplateResponseDto> update(CustomTemplateDto customTemplateDto, String id) {
    final Mono<CustomTemplate> template = customTemplateRepository.findById(id);
    template.subscribe(System.out::println);
    return create(customTemplateDto);
  }

  /**
   * Find all flux.
   *
   * @return the flux
   */
  public Flux<CustomTemplateDto> findAll() {
    return customTemplateRepository.findAll()
        .map(this::convertToDto);
  }

  /**
   * Find all flux.
   *
   * @param fields
   *     the fields
   * @param limit
   *     the limit
   *
   * @return the flux
   */
  public ResponseEntity<List<TemplateResponseDto>> findAllFromWhatsApp(String fields, int limit) {
    try {
      final ResponseEntity<Map<String, Object>> response = webClientWrapper.getCustomTemplates(fields, limit);

      if (response.getStatusCode() == HttpStatus.OK) {
        List<TemplateResponseDto> templates = parseTemplateResponse(response.getBody());
        return ResponseEntity.ok(templates);
      } else {
        return ResponseEntity.status(response.getStatusCode()).build();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Find by id mono.
   *
   * @param id
   *     the id
   *
   * @return the mono
   */
  public Mono<CustomTemplateDto> findById(String id) {
    return customTemplateRepository.findById(id)
        .map(this::convertToDto);
  }

  /**
   * Delete by id mono.
   *
   * @param id
   *     the id
   *
   * @return the mono
   */
  public Mono<Void> deleteById(String id) {
    return customTemplateRepository.deleteById(id);
  }

  private List<TemplateResponseDto> parseTemplateResponse(Map<String, Object> responseBody) {
    List<Map<String, Object>> data = (List<Map<String, Object>>) responseBody.get("data");
    List<TemplateResponseDto> templates = new ArrayList<>();

    for (Map<String, Object> templateData : data) {
      TemplateResponseDto template = new TemplateResponseDto();
      template.setName((String) templateData.get("name"));
      template.setStatus((String) templateData.get("status"));
      template.setId((String) templateData.get("id"));
      templates.add(template);
    }

    return templates;
  }

  private CustomTemplateDto convertToDto(CustomTemplate customTemplate) {
    return modelMapper.map(customTemplate, CustomTemplateDto.class);
  }
  private CustomTemplateResponseDto convertResponseToDto(CustomTemplate customTemplate) {
    return modelMapper.map(customTemplate, CustomTemplateResponseDto.class);
  }
  private CustomTemplate convertToEntity(CustomTemplateDto customTemplateDto) {

    return modelMapper.map(customTemplateDto, CustomTemplate.class);
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
    try {
     customTemplateRepository.deleteByResponseId(hsmId);
     return webClientWrapper.deleteTemplate(name, hsmId);



    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }}
}
