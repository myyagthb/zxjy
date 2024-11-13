package edu.hut.aiassistant.resp;

import lombok.Data;

@Data
public class UserInfoResp {
    private Long userId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 角色
     */
    private Integer role;

    /**
     * 教育阶段
     */
    private String educateStage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private String licenseType;

    /**
     * 证件号码
     */
    private String licenseNumber;
    /**
     * 学校
     */
    private String school;
}
