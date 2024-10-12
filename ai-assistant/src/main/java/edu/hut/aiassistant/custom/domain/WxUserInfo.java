package edu.hut.aiassistant.custom.domain;

import lombok.Data;

/**
 * 微信用户信息实体
 */
@Data
public class WxUserInfo {
    private String openid;
    private String nickname;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
}
