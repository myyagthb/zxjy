package edu.hut.aiassistant.req;


import lombok.Data;

@Data
public class SearchParamsReq {
    /**
     * 当前页数
     */
    private Integer currentPage;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 搜索关键字
     */
    private String searchText;
    /**
     * 当前用户ID
     */
    private Long userId;
}
