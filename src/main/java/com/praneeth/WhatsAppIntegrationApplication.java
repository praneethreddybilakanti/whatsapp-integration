package com.praneeth;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * The type Whats app integration application.
 */
@SpringBootApplication
@EnableFeignClients
public class WhatsAppIntegrationApplication {

  /**
   * The entry point of application.
   *
   * @param args
   *     the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(WhatsAppIntegrationApplication.class, args);
  }

  /**
   * Rest template rest template.
   *
   * @param builder
   *     the builder
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  /**
   * Model mapper model mapper.
   *
   * @return the model mapper
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  /**
   * The type Health check.
   */
  @RestController("/")
  class HealthCheck {
    /**
     * Index health string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String indexHealth() {
      return "indexHealth";
    }

    /**
     * Application health string.
     *
     * @return the string
     */
    @GetMapping("social-marketing-communication/whatsapp/")
    public String applicationHealth() {
      return "ConfigServiceApplication Healthy!!!";
    }
  }
}

