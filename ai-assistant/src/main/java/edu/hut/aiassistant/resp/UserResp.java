package edu.hut.aiassistant.resp;

import lombok.Data;

@Data
public class UserResp {
    private String token;//加密token
    private Integer role;//角色身份
}
