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

    /**
     * 根据用户id获取用户添加的课程列表
     * @param userId userId
     * @return r
     */
    R getCourseListByUserId(Long userId);
}
