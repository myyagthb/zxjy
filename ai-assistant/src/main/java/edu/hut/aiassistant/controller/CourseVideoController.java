package edu.hut.aiassistant.controller;

import edu.hut.aiassistant.custom.service.CourseVideoServiceCustom;
import edu.hut.aiassistant.req.CourseVideoReq;
import edu.hut.aiassistant.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseVideoController
 * @Author 王海鑫
 * @Date 2024/11/18 16:22:35
 * @Description TODO
 */
@RestController
@RequestMapping("/course-video")
public class CourseVideoController {

    @Autowired
    private CourseVideoServiceCustom courseVideoServiceCustom;


    @PostMapping("/addCourseVideo")
    public R addCourseVideo(@ModelAttribute CourseVideoReq courseVideoReq){
        return courseVideoServiceCustom.addCourseVideo(courseVideoReq);
    }
}
