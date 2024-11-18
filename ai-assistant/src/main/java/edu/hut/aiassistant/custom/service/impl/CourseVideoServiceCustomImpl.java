package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import edu.hut.aiassistant.custom.service.CourseVideoServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.generator.domain.CourseVideo;
import edu.hut.aiassistant.generator.mapper.CourseVideoMapper;
import edu.hut.aiassistant.req.CourseVideoReq;
import edu.hut.aiassistant.resp.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CourseVideoServiceCustomImpl
 * @Author 王海鑫
 * @Date 2024/11/18 01:31:50
 * @Description TODO
 */
@Service
public class CourseVideoServiceCustomImpl implements CourseVideoServiceCustom {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseVideoServiceCustomImpl.class);


    @Autowired
    private CourseVideoMapper courseVideoMapper;


    @Override
    public R addCourseVideo(CourseVideoReq courseVideoReq) {
        LOGGER.info("正在添加课程视频请求对象：{}",courseVideoReq);
        if (courseVideoReq.getCourseId() == null || courseVideoReq.getUserId() == null){
            return new R(RespEnum.FAIL.getCode(), "课程参数错误",null);
        }
        if (courseVideoReq.getVideoName() == null || courseVideoReq.getVideoName().isEmpty()){
            return new R(RespEnum.FAIL.getCode(), "视频名称不能为空",null);
        }
        if (courseVideoReq.getBelongPassage() == null || courseVideoReq.getBelongPassage().isEmpty()){
            return new R(RespEnum.FAIL.getCode(), "视频所属目录章节不能为空",null);
        }

        //获取当前时间
        DateTime now = DateTime.now();
        CourseVideo courseVideo = BeanUtil.copyProperties(courseVideoReq, CourseVideo.class);
        //TODO 这里后期需要处理视频保存路径问题
        courseVideo.setCreateTime(now);
        courseVideo.setUpdateTime(now);

        //将数据保存到数据库
        courseVideoMapper.insert(courseVideo);
        return new R(RespEnum.SUCCESS.getCode(), "课程视频上传成功",null);
    }

    @Override
    public R getCourseVideo(Long courseId) {
        return null;
    }
}
