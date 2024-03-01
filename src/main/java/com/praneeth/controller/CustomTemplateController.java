package com.praneeth.controller;

import com.praneeth.dto.response.TemplateResponseDto;
import com.praneeth.dto.template.CustomTemplateDto;
import com.praneeth.dto.template.CustomTemplateResponseDto;
import com.praneeth.dto.template.DeleteResponse;
import com.praneeth.service.impl.CustomTemplateServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The type Custom template controller.
 */
@RestController
@RequestMapping("social-marketing-communication/whatsapp/v1/custom-templates")
public class CustomTemplateController {

  private final CustomTemplateServiceImpl customTemplateService;


  /**
   * Instantiates a new Custom template controller.
   *
   * @param customTemplateService
   *     the custom template service
   */
  public CustomTemplateController(final CustomTemplateServiceImpl customTemplateService) {
    this.customTemplateService = customTemplateService;
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  @GetMapping
  public Flux<CustomTemplateDto> getAll() {
    return customTemplateService.findAll();

  }

  /**
   * Gets template by id.
   *
   * @param id
   *     the id
   *
   * @return the template by id
   */
  @GetMapping("/{id}")
  public Mono<ResponseEntity<CustomTemplateDto>> getTemplateById(@PathVariable String id) {
    return customTemplateService.findById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * Create template mono.
   *
   * @param customTemplateDto
   *     the custom template dto
   *
   * @return the mono
   */
  @PostMapping
  public Mono<ResponseEntity<CustomTemplateResponseDto>> createTemplate(@RequestBody CustomTemplateDto customTemplateDto){
    return customTemplateService.create(customTemplateDto)
        .map(customTemplateDto1 -> ResponseEntity.status(HttpStatus.CREATED).body(customTemplateDto1));
  }

  /**
   * Update template mono.
   *
   * @param id
   *     the id
   * @param customTemplateDto
   *     the custom template dto
   *
   * @return the mono
   */
  @PutMapping("/{id}")
  public Mono<ResponseEntity<CustomTemplateResponseDto>> updateTemplate(@PathVariable String id, @RequestBody CustomTemplateDto customTemplateDto) {
    return customTemplateService.update(customTemplateDto, id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  /**
   * Delete template mono.
   *
   * @param id
   *     the id
   *
   * @return the mono
   */
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteTemplate(@PathVariable String id) {
    return customTemplateService.deleteById(id)
        .then(Mono.just(ResponseEntity.ok().<Void>build()))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }


  /**
   * Gets templates.
   *
   * @param fields
   *     the fields
   * @param limit
   *     the limit
   *
   * @return the templates
   */
  @GetMapping("fb")
    public ResponseEntity<List<TemplateResponseDto>> getTemplates(
        @RequestParam(value = "fields", defaultValue = "name,status") String fields,
        @RequestParam(value = "limit", defaultValue = "10") int limit) {

  return customTemplateService.findAllFromWhatsApp(fields,limit);
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
  @DeleteMapping
  public ResponseEntity<DeleteResponse> deleteTemplate(@RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "hsm_id", required = false) String hsmId) {
    return customTemplateService.deleteTemplate(name,hsmId);


  }


}
