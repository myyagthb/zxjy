package edu.hut.aiassistant.custom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiXinToken {
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String is_snapshotuser;
    private String unionid;
}
