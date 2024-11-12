package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.service.CategoryServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.Category;
import edu.hut.aiassistant.generator.mapper.CategoryMapper;
import edu.hut.aiassistant.req.CategoryReq;
import edu.hut.aiassistant.resp.CategoryResp;
import edu.hut.aiassistant.resp.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceCustomImpl
 * @Author 王海鑫
 * @Date 2024/11/11 下午6:57
 * @Description TODO
 */
@Service
public class CategoryServiceCustomImpl implements CategoryServiceCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceCustomImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 添加种类
     * @param categoryReq
     * @return
     */
    @Override
    public R addCategory(CategoryReq categoryReq) {
        LOGGER.info("正在添加种类对象：{}", categoryReq);
        if (categoryReq == null || categoryReq.getCategoryName() == null || categoryReq.getCategoryName().trim() == "") {
            return new R(RespEnum.FAIL.getCode(), "种类名称不能为空",null);
        }
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCategoryName, categoryReq.getCategoryName().trim());
        if (categoryMapper.selectCount(queryWrapper) > 0) {
            return new R(RespEnum.FAIL.getCode(), "该种类已存在",null);
        }
        DateTime now = DateTime.now();
        Category category = new Category();
        category.setCategoryName(categoryReq.getCategoryName().trim());
        category.setParentId(categoryReq.getParentId());
        if(categoryReq.getParentId() == null ){
            //如果用户没指定父级类，则默认为第一级类
            category.setParentId(0);
        }
        category.setCategoryId(categoryReq.getCategoryId());
        category.setCreateTime(now);
        category.setUpdateTime(now);
        categoryMapper.insert(category);
        return new R(RespEnum.SUCCESS.getCode(),"种类添加成功" ,null);
    }

    /**
     * 查询种类
     * @return
     */
    @Override
    public R queryCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        List<CategoryResp> categoryRespList = BeanUtil.copyToList(categoryList, CategoryResp.class);
        return new R(RespEnum.SUCCESS.getCode(), "种类查询成功",categoryRespList);
    }
}
