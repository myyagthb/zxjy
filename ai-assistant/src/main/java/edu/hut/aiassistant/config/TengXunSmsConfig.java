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
@ConfigurationProperties(prefix = "tengxun")
public class TengXunSmsConfig {
    private String secretId;
    private String secretKey;
    private int sdkAppId;
    private String signName;
    private int templateId;
}
