package edu.hut.aiassistant.resp;

import lombok.Data;

/**
 * @ClassName CourseResp
 * @Author 王海鑫
 * @Date 2024/11/18 15:06:11
 * @Description TODO
 */
@Data
public class CourseResp {
    /**
     * 课程id
     */
    private Long courseId;
    /**
     * 课程名称
     */
    private String courseName;
}
