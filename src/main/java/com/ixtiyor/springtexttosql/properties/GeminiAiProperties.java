package com.ixtiyor.springtexttosql.properties;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Data
@EnableAsync
@Configuration
@ConfigurationProperties(prefix = "gemini-ai")
public class GeminiAiProperties {

    private String host;
    @JsonProperty("api-key")
    private String apiKey;
    private Long timeout;

}