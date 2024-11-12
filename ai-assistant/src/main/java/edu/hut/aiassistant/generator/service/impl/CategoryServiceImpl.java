package edu.hut.aiassistant.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hut.aiassistant.generator.domain.Category;
import edu.hut.aiassistant.generator.service.CategoryService;
import edu.hut.aiassistant.generator.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海鑫
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2024-11-11 20:46:00
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




