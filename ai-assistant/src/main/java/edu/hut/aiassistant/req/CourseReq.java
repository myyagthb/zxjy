package edu.hut.aiassistant.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CourseReq
 * @Author 王海鑫
 * @Date 2024/11/11 上午10:45
 * @Description TODO
 */
@Data
public class CourseReq {
    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程分类
     */
    private String courseCategory;

    /**
     * 课程标签
     */
    private String[] chooseTagList;

    /**
     * 课程讲师
     */
    private String courseTeacher;

    /**
     * 公开状态
     */
    private String publicState;

    /**
     * 课程简介
     */
    private String courseBlurb;

    /**
     * 课程存放路径
     */
    private String courseUrl;

    /**
     * 课程封面路径
     */
    private MultipartFile courseCovers;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 添加课程的用户Id
     */
    private Long userId;
}
