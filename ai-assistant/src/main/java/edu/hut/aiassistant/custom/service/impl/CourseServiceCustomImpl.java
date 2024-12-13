package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hut.aiassistant.config.StorageApiConfig;
import edu.hut.aiassistant.custom.service.CourseServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.enums.SystemExceptionEnum;
import edu.hut.aiassistant.exception.SystemException;
import edu.hut.aiassistant.generator.domain.Course;
import edu.hut.aiassistant.generator.mapper.CourseMapper;
import edu.hut.aiassistant.req.CourseReq;
import edu.hut.aiassistant.req.SearchParamsReq;
import edu.hut.aiassistant.resp.CourseResp;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private StorageApiConfig storageApiConfig;

    @Autowired
    private CourseMapper courseMapper;


    //添加线程池，作异步上传文件
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R uploadCourse(CourseReq courseReq) {
        LOGGER.info("课程请求对象：{}", courseReq);
        //判断课程是否存在
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<Course>();
        queryWrapper.eq(Course::getCourseName, courseReq.getCourseName().trim());
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        if (!courseList.isEmpty()){
            LOGGER.info("课程已存在：{}", courseReq.getCourseName());
            return new R(RespEnum.FAIL.getCode(), "该课程已存在",null);
        }
        //获取时间
        DateTime now = DateTime.now();
        //封装课程保存信息
        Course newCourse = new Course();
        newCourse.setCourseName(courseReq.getCourseName());
        newCourse.setCourseCategory(courseReq.getCourseCategory());
        newCourse.setCourseTag(Arrays.toString(courseReq.getChooseTagList()));
        newCourse.setCourseTeacher(courseReq.getCourseTeacher());
        newCourse.setPublicState(courseReq.getPublicState());
        newCourse.setCourseBlurb(courseReq.getCourseBlurb());

        //随即生成文件ID名称
        MultipartFile courseCoversFile = courseReq.getCourseCovers();
        // 生成唯一的文件名，并以此作为存入云端的id
        String courseUrl = UUID.randomUUID() + Objects.requireNonNull(courseCoversFile.getOriginalFilename()).substring(courseCoversFile.getOriginalFilename().lastIndexOf("."));
        newCourse.setCourseUrl(courseUrl);

        newCourse.setCreateTime(now);
        newCourse.setUpdateTime(now);
        newCourse.setUserId(courseReq.getUserId());


        //保存课程信息到数据库
        LOGGER.info("准备插入课程：{}", newCourse);
        int insertCourse = courseMapper.insert(newCourse);

        threadPoolExecutor.execute(() -> {
            handlerCourseCover(courseReq.getCourseCovers(),courseUrl);
        });

        LOGGER.info("插入结果：{}", insertCourse);
        if (insertCourse > 0) {
            return new R(RespEnum.SUCCESS.getCode(), "课程添加成功",null);
        }
        return new R(RespEnum.FAIL.getCode(), "课程添加失败",null);
    }

    private void handlerCourseCover(MultipartFile file,String uniqueFileName)  {
        try {
            if (file.isEmpty()){
                throw new SystemException(SystemExceptionEnum.FILE_CANNOT_NULL);
            }
            String url = storageApiConfig.getUri() + "/object/test/" + uniqueFileName;
            //构造请求头部
            Map<String,Object> headers = new HashMap<>();
            headers.put("x-forwarded-host",storageApiConfig.getXForwardedHost());
            headers.put("authorization",storageApiConfig.getAuthorization());
            //构造请求参数
            Map<String,Object> params = new HashMap<>();
            params.put("file",file);
            //发送文件保存请求
            HttpClientUtils.doPost(url,headers,params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public R getCourseListByUserId(Long userId) {
        if (userId == null){
            return new R(RespEnum.FAIL.getCode(), "用户ID不能为空，请重新登录",null);
        }
        LOGGER.info("正在查询用户ID为：{}的用户课程列表",userId);
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<Course>();
        queryWrapper.eq(Course::getUserId, userId);
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        LOGGER.info("查询结果：{}",courseList);
        List<CourseResp> courseRespList = BeanUtil.copyToList(courseList, CourseResp.class);
        return new R(RespEnum.SUCCESS.getCode(), "课程结果查询成功",courseRespList);
    }

    @Override
    public R getCourseList() {

        LOGGER.info("正在查询课程列表");
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<Course>();
        List<Course> courseList = courseMapper.selectList(queryWrapper);
        LOGGER.info("查询结果：{}",courseList);
        List<CourseResp> courseRespList = BeanUtil.copyToList(courseList, CourseResp.class);
        return new R(RespEnum.SUCCESS.getCode(), "课程结果查询成功",courseRespList);
    }


    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Throwable.class)
    @Override
    public IPage<Course> searchCourseBySearchText(SearchParamsReq searchParamsReq) {
        // 确保 userId 是必需的
        if (searchParamsReq.getUserId() == null) {
            // 抛出自定义用户ID不能为空异常
            throw new SystemException(SystemExceptionEnum.USER_ID_CANNOT_NULL);
        }

        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Course::getUserId, searchParamsReq.getUserId());

        if (StringUtils.isNotBlank(searchParamsReq.getSearchText())) {
            String searchText = searchParamsReq.getSearchText().trim();
            lqw.and(q -> q.or(subQ -> subQ.like(Course::getCourseName, searchText))
                    .or(subQ -> subQ.like(Course::getCourseBlurb, searchText)));
        }

        IPage<Course> page = new Page<>(searchParamsReq.getCurrentPage(), searchParamsReq.getPageSize());
        courseMapper.selectPage(page, lqw);
        return page;
    }


}
