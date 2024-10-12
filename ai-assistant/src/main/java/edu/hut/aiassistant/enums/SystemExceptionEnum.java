package edu.hut.aiassistant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemExceptionEnum {
    PARAMS_EXCEPTION("参数异常");
    final String desc;
}
