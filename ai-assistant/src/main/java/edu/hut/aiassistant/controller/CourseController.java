package edu.hut.aiassistant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import edu.hut.aiassistant.custom.service.CourseServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.Course;
import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.req.SearchParamsReq;
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


    @PostMapping("/searchCourseBySearchText")
    public R searchCourseBySearchText(@RequestBody SearchParamsReq searchParamsReq) {
        IPage<Course> courseIPage = courseServiceCustom.searchCourseBySearchText(searchParamsReq);
        return new R(RespEnum.SUCCESS.getCode(), "查询成功", courseIPage);
    }
}
