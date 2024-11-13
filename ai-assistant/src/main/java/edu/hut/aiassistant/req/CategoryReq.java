package edu.hut.aiassistant.req;

import lombok.Data;

/**
 * @ClassName CategoryReq
 * @Author 王海鑫
 * @Date 2024/11/11 下午6:58
 * @Description TODO
 */
@Data
public class CategoryReq {
    private Integer categoryId;
    private String categoryName;;
    private Integer parentId;
}
