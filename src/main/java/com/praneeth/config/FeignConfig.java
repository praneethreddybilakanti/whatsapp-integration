package com.praneeth.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * The type Feign config.
 */
public class FeignConfig {

  /**
   * Feign logger level logger . level.
   *
   * @return the logger . level
   */
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}