package edu.hut.aiassistant.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxUserLoginReq {
    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 验证码
     */
    private Integer code;
    /**
     * 用户ID
     */
    private Integer userId;
}
