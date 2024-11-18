package edu.hut.aiassistant.custom.service;

import edu.hut.aiassistant.req.CourseVideoReq;
import edu.hut.aiassistant.resp.R;

/**
 * @ClassName CouseVideoServiceCustom
 * @Author 王海鑫
 * @Date 2024/11/18 01:31:22
 * @Description TODO
 */
public interface CourseVideoServiceCustom {

    R addCourseVideo(CourseVideoReq courseVideoReq);

    R getCourseVideo(Long courseId);
}
