package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.custom.service.CourseServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.Course;
import edu.hut.aiassistant.generator.mapper.CourseMapper;
import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.resp.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CourseServiceCustomImpl
 * @Author 王海鑫
 * @Date 2024/11/11 上午10:43
 * @Description TODO
 */
@Service
public class CourseServiceCustomImpl implements CourseServiceCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceCustomImpl.class);


    @Autowired
    private CourseMapper courseMapper;

    @Override
    public R uploadCourse(CourseReq courseReq) {
        LOGGER.info("课程请求对象：{}", courseReq);
        //判断课程是否存在
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<Course>();
        queryWrapper.eq(Course::getCourseName, courseReq.getCourseName().trim());
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        if (!courseList.isEmpty()){
            return new R(RespEnum.FAIL.getCode(), "该课程已存在",null);
        }

        DateTime now = DateTime.now();
        Course newCourse = new Course();
        newCourse.setCourseName(courseReq.getCourseName());
        newCourse.setCourseCategory(courseReq.getCourseCategory());
        newCourse.setCourseTag(Arrays.toString(courseReq.getChooseTagList()));
        newCourse.setCourseTeacher(courseReq.getCourseTeacher());
        newCourse.setPublicState(courseReq.getPublicState());
        newCourse.setCourseBlurb(courseReq.getCourseBlurb());
        newCourse.setCourseUrl(courseReq.getCourseUrl());
        newCourse.setCreateTime(now);
        newCourse.setUpdateTime(now);

        //TODO 图片路径，封面存储还有待处理

        //保存课程信息到数据库
        int insertCourse = courseMapper.insert(newCourse);
        if (insertCourse > 0) {
            return new R(RespEnum.SUCCESS.getCode(), "课程添加成功",null);
        }
        return new R(RespEnum.FAIL.getCode(), "课程添加失败",null);
    }
}
