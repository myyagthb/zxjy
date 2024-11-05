package edu.hut.aiassistant.resp;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
public class UserResp implements Serializable {

    @Serial
    private static final long serialVersionUID = -4844798999419046644L;
    private Long id;
    /**
     * 小程序用户的openid
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别 0-男、1-女
     */
    private Integer gender;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;


    /**
     * 创建/注册时间
     */
    private Date createTime;

    private Date updateTime;

    private String mobile;

}
