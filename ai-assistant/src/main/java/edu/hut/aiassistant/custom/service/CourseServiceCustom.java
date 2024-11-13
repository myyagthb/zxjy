package edu.hut.aiassistant.custom.service;

import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.resp.R;

/**
 * @ClassName CourseServiceCustom
 * @Author 王海鑫
 * @Date 2024/11/11 上午10:41
 * @Description TODO
 */
public interface CourseServiceCustom {
    R uploadCourse(CourseReq courseReq);
}
