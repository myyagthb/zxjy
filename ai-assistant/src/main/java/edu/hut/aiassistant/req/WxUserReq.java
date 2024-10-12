package edu.hut.aiassistant.req;

import lombok.Data;

@Data
public class WxUserReq {


    /**
     * 用户ID
     */
    private Long id;
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
}
