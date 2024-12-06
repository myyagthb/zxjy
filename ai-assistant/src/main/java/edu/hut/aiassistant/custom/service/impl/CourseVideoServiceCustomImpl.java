package edu.hut.aiassistant.custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.hut.aiassistant.config.StorageApiConfig;
import edu.hut.aiassistant.custom.service.CourseVideoServiceCustom;
import edu.hut.aiassistant.enums.RespEnum;
import edu.hut.aiassistant.enums.SystemExceptionEnum;
import edu.hut.aiassistant.exception.SystemException;
import edu.hut.aiassistant.generator.domain.CourseVideo;
import edu.hut.aiassistant.generator.mapper.CourseVideoMapper;
import edu.hut.aiassistant.req.CourseVideoReq;
import edu.hut.aiassistant.resp.R;
import edu.hut.aiassistant.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StorageApiConfig storageApiConfig;


    //添加线程池，作异步上传文件
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));


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

        //判断视频是否上传
        LambdaQueryWrapper<CourseVideo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseVideo::getVideoName, courseVideoReq.getVideoName())
                .eq(CourseVideo::getBelongPassage, courseVideoReq.getBelongPassage())
                .eq(CourseVideo::getUserId, courseVideoReq.getUserId());
        if (courseVideoMapper.selectOne(lambdaQueryWrapper) != null){
            return new R(RespEnum.SUCCESS.getCode(), "改章节视频以上上传",null);
        }

        //获取当前时间
        DateTime now = DateTime.now();
        CourseVideo courseVideo = BeanUtil.copyProperties(courseVideoReq, CourseVideo.class);
        //TODO 这里后期需要处理视频保存路径问题
        //随即生成文件ID名称
//        MultipartFile courseVideoReqVideoFile = courseVideoReq.getVideoFile();
//        // 生成唯一的文件名，并以此作为存入云端的id
//        String courseVideoUrl = UUID.randomUUID() + Objects.requireNonNull(courseVideoReqVideoFile.getOriginalFilename()).substring(courseVideoReqVideoFile.getOriginalFilename().lastIndexOf("."));
//        courseVideo.setVideoUrl(courseVideoUrl);


        courseVideo.setCreateTime(now);
        courseVideo.setUpdateTime(now);

        //将数据保存到数据库
        courseVideoMapper.insert(courseVideo);

        //异步上传视频到服务
//        threadPoolExecutor.execute(() -> {
//            handlerUploadVideo(courseVideoReq.getVideoFile(),courseVideoUrl);
//        });

        return new R(RespEnum.SUCCESS.getCode(), "课程视频上传成功",null);
    }

    private void handlerUploadVideo(MultipartFile file, String uniqueFileName)  {
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
    public R getCourseVideo(Long courseId) {
        return null;
    }
}
