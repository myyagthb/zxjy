package edu.hut.aiassistant.custom.service;

import edu.hut.aiassistant.req.CategoryReq;
import edu.hut.aiassistant.resp.R;

/**
 * @ClassName CategoryServiceImpl
 * @Author 王海鑫
 * @Date 2024/11/11 下午6:57
 * @Description TODO
 */
public interface CategoryServiceCustom {
    R addCategory(CategoryReq categoryReq);

    R queryCategoryList();
}
