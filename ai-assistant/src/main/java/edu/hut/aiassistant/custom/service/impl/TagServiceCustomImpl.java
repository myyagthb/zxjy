package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.service.TagServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.Tag;
import edu.hut.aiassistant.generator.mapper.TagMapper;
import edu.hut.aiassistant.req.TagReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.resp.TagResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TagServiceCustomImpl
 * @Author 王海鑫
 * @Date 2024/11/11 下午4:42
 * @Description TODO
 */
@Service
public class TagServiceCustomImpl implements TagServiceCustom {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceCustomImpl.class);



    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加标签
     * @param tagReq
     * @return
     */
    @Override
    public R addTag(TagReq tagReq) {
        LOGGER.info("正在添加种类对象：{}", tagReq);
        if (tagReq == null || tagReq.getTagName() == null || tagReq.getTagName().trim() == "") {
            return new R(RespEnum.FAIL.getCode(), "标签名称不能为空",null);
        }
        //判断标签是否存在，避免重复
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getTagName, tagReq.getTagName().trim());
        List<Tag> tagList = tagMapper.selectList(queryWrapper);
        if (!tagList.isEmpty()) {
            return new R(RespEnum.FAIL.getCode(), "改标签已存在",null);
        }
        DateTime now = DateTime.now();
        Tag tag = new Tag();
        tag.setTagName(tagReq.getTagName().trim());
        tag.setCreateTime(now);
        tag.setUpdateTime(now);
        //保存tag标签数据库
        tagMapper.insert(tag);
        return new R(RespEnum.SUCCESS.getCode(), "标签保存成功",null);
    }

    /**
     * 查询标签
     * @return
     */
    @Override
    public R queryTagList() {
        List<Tag> tagList = tagMapper.selectList(null);
        List<TagResp> tagRespList = BeanUtil.copyToList(tagList, TagResp.class);
        return new R(RespEnum.SUCCESS.getCode(), "标签查询成功",tagRespList);
    }
}
