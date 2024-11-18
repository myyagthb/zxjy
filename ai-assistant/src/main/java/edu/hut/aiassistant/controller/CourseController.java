package edu.hut.aiassistant.controller;

import edu.hut.aiassistant.custom.service.CourseServiceCustom;
import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CourseController
 * @Author 王海鑫
 * @Date 2024/11/12 18:33:40
 * @Description TODO
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseServiceCustom courseServiceCustom;

    @PostMapping("/uploadCourse")
    public R uploadCourse(@ModelAttribute CourseReq courseReq) {
        return courseServiceCustom.uploadCourse(courseReq);
    }


    @GetMapping("/getCourseListByUserId")
    public R getCourseListByUserId(@RequestParam Long userId) {
        return courseServiceCustom.getCourseListByUserId(userId);
    }
}
