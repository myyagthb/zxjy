package edu.hut.aiassistant.resp;

import lombok.Data;

/**
 * @ClassName TagReq
 * @Author 王海鑫
 * @Date 2024/11/11 下午4:43
 * @Description 标签类
 */
@Data
public class TagResp {
    /**
     * 标签ID
     */
    private Integer tagId;
    /**
     * 标签名称
     */
    private String tagName;
}
