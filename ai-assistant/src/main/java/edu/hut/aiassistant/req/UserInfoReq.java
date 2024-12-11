package edu.hut.aiassistant.req;

import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoReq {
    @NotNull(message = "【用户ID】不能为空")
    private Long userId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;    
    /**
     * 角色
     */
    private Integer role;

    /**
     * 教育阶段
     */
    private String educateStage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 证件类型
     */
    private String licenseType;

    /**
     * 证件号码
     */
    private String licenseNumber;

    /**
     * 学校
     */
    private String school;
}
