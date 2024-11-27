package edu.hut.aiassistant.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName CourseVideoReq
 * @Author 王海鑫
 * @Date 2024/11/18 01:32:27
 * @Description TODO
 */
@Data
public class CourseVideoReq {

     /**
     * 所属课程ID
     */
    private Long courseId;

    /**
     * 上传人的ID
     */
    private Long userId;

    /**
     * 所属章节
     */
    private String belongPassage;

    /**
     * 视频名称
     */
    private String videoName;


    /**
     * 视频文件
     */
    private MultipartFile videoFile;

    /**
     * 视频保存路径
     */
    private String videoUrl;
}
