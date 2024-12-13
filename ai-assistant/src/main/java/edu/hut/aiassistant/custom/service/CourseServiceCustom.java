package edu.hut.aiassistant.custom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hut.aiassistant.generator.domain.Course;
import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.req.SearchParamsReq;
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

    /**
     * 返回所有课程列表
     * @param void
     * @return r
     */
    R getCourseList();

    /**
     * 根据搜索内容查询课程信息
     * @param searchParamsReq searchParamsReq
     * @return
     */
    IPage<Course> searchCourseBySearchText(SearchParamsReq searchParamsReq);
}
