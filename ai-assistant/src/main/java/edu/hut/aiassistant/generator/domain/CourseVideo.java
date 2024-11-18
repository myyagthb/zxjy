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
 * @TableName t_course_video
 */
@TableName(value ="t_course_video")
@Data
public class CourseVideo implements Serializable {
    /**
     * 视频ID
     */
    @TableId
    private Long videoId;

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
     * 保存路径
     */
    private String videoUrl;

    /**
     * 视频描述
     */
    private String videoDescription;

    /**
     * 预存字段2
     */
    private String prestoreField2;

    /**
     * 预存字段3
     */
    private String prestoreField3;

    /**
     * 预存字段4
     */
    private String prestoreField4;

    /**
     * 预存字段5
     */
    private String prestoreField5;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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
        CourseVideo other = (CourseVideo) that;
        return (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBelongPassage() == null ? other.getBelongPassage() == null : this.getBelongPassage().equals(other.getBelongPassage()))
            && (this.getVideoName() == null ? other.getVideoName() == null : this.getVideoName().equals(other.getVideoName()))
            && (this.getVideoUrl() == null ? other.getVideoUrl() == null : this.getVideoUrl().equals(other.getVideoUrl()))
            && (this.getVideoDescription() == null ? other.getVideoDescription() == null : this.getVideoDescription().equals(other.getVideoDescription()))
            && (this.getPrestoreField2() == null ? other.getPrestoreField2() == null : this.getPrestoreField2().equals(other.getPrestoreField2()))
            && (this.getPrestoreField3() == null ? other.getPrestoreField3() == null : this.getPrestoreField3().equals(other.getPrestoreField3()))
            && (this.getPrestoreField4() == null ? other.getPrestoreField4() == null : this.getPrestoreField4().equals(other.getPrestoreField4()))
            && (this.getPrestoreField5() == null ? other.getPrestoreField5() == null : this.getPrestoreField5().equals(other.getPrestoreField5()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBelongPassage() == null) ? 0 : getBelongPassage().hashCode());
        result = prime * result + ((getVideoName() == null) ? 0 : getVideoName().hashCode());
        result = prime * result + ((getVideoUrl() == null) ? 0 : getVideoUrl().hashCode());
        result = prime * result + ((getVideoDescription() == null) ? 0 : getVideoDescription().hashCode());
        result = prime * result + ((getPrestoreField2() == null) ? 0 : getPrestoreField2().hashCode());
        result = prime * result + ((getPrestoreField3() == null) ? 0 : getPrestoreField3().hashCode());
        result = prime * result + ((getPrestoreField4() == null) ? 0 : getPrestoreField4().hashCode());
        result = prime * result + ((getPrestoreField5() == null) ? 0 : getPrestoreField5().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", videoId=").append(videoId);
        sb.append(", courseId=").append(courseId);
        sb.append(", userId=").append(userId);
        sb.append(", belongPassage=").append(belongPassage);
        sb.append(", videoName=").append(videoName);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", videoDescription=").append(videoDescription);
        sb.append(", prestoreField2=").append(prestoreField2);
        sb.append(", prestoreField3=").append(prestoreField3);
        sb.append(", prestoreField4=").append(prestoreField4);
        sb.append(", prestoreField5=").append(prestoreField5);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}