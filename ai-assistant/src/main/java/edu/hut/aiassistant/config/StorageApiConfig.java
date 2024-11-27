package edu.hut.aiassistant.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "storage-api")
public class StorageApiConfig {
    private String uri;//http请求路径
    private String authorization;
    private String xForwardedHost;
}
