package edu.hut.aiassistant.custom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("wx")
public class WeiXinAccountConfig {
    private String appId;//微信账户的appId
    private String appSecret;//微信账户的appSecret
    private String token;
    private String domain;
    private String redirectUri;
    private String templateId;
}
