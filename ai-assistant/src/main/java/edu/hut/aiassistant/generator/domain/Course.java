package edu.hut.aiassistant.generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_course
 */
@TableName(value ="t_course")
@Data
public class Course implements Serializable {
    /**
     * 课程ID
     */
    @TableId
    private Long courseId;

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
    private String courseTag;

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
    private String courseCovers;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上传者ID
     */
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getCourseName() == null ? other.getCourseName() == null : this.getCourseName().equals(other.getCourseName()))
            && (this.getCourseCategory() == null ? other.getCourseCategory() == null : this.getCourseCategory().equals(other.getCourseCategory()))
            && (this.getCourseTag() == null ? other.getCourseTag() == null : this.getCourseTag().equals(other.getCourseTag()))
            && (this.getCourseTeacher() == null ? other.getCourseTeacher() == null : this.getCourseTeacher().equals(other.getCourseTeacher()))
            && (this.getPublicState() == null ? other.getPublicState() == null : this.getPublicState().equals(other.getPublicState()))
            && (this.getCourseBlurb() == null ? other.getCourseBlurb() == null : this.getCourseBlurb().equals(other.getCourseBlurb()))
            && (this.getCourseUrl() == null ? other.getCourseUrl() == null : this.getCourseUrl().equals(other.getCourseUrl()))
            && (this.getCourseCovers() == null ? other.getCourseCovers() == null : this.getCourseCovers().equals(other.getCourseCovers()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getCourseName() == null) ? 0 : getCourseName().hashCode());
        result = prime * result + ((getCourseCategory() == null) ? 0 : getCourseCategory().hashCode());
        result = prime * result + ((getCourseTag() == null) ? 0 : getCourseTag().hashCode());
        result = prime * result + ((getCourseTeacher() == null) ? 0 : getCourseTeacher().hashCode());
        result = prime * result + ((getPublicState() == null) ? 0 : getPublicState().hashCode());
        result = prime * result + ((getCourseBlurb() == null) ? 0 : getCourseBlurb().hashCode());
        result = prime * result + ((getCourseUrl() == null) ? 0 : getCourseUrl().hashCode());
        result = prime * result + ((getCourseCovers() == null) ? 0 : getCourseCovers().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseCategory=").append(courseCategory);
        sb.append(", courseTag=").append(courseTag);
        sb.append(", courseTeacher=").append(courseTeacher);
        sb.append(", publicState=").append(publicState);
        sb.append(", courseBlurb=").append(courseBlurb);
        sb.append(", courseUrl=").append(courseUrl);
        sb.append(", courseCovers=").append(courseCovers);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}