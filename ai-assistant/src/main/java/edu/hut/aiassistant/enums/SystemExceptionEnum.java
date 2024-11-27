package edu.hut.aiassistant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemExceptionEnum {
    PARAMS_EXCEPTION("参数异常"),
    FILE_CANNOT_NULL("文件不能为空"),
    USER_ID_CANNOT_NULL("用户ID不能为空");
    final String desc;
}
