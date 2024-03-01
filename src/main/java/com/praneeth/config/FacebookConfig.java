package com.praneeth.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The type Facebook config.
 */
@Configuration
@ConfigurationProperties(prefix = "facebook")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacebookConfig {

  private String apiVersion;
  private String myNumberId;
  private String accessToken;
  private String recipientNumber;
  private String verifyToken;
  private String version;
  private String appId;
  // getters and setters
  private String wabaId;
}
