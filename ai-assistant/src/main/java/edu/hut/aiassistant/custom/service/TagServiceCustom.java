package edu.hut.aiassistant.custom.service;

import edu.hut.aiassistant.req.TagReq;
import edu.hut.aiassistant.resp.R;

/**
 * @ClassName TagServiceCustom
 * @Author 王海鑫
 * @Date 2024/11/11 下午4:42
 * @Description TODO
 */
public interface TagServiceCustom {
    R addTag(TagReq tagReq);

    R queryTagList();

}
