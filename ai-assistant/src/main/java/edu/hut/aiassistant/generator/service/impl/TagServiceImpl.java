package edu.hut.aiassistant.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.hut.aiassistant.generator.domain.Tag;
import edu.hut.aiassistant.generator.service.TagService;
import edu.hut.aiassistant.generator.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 王海鑫
* @description 针对表【t_tag】的数据库操作Service实现
* @createDate 2024-11-11 16:55:22
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




