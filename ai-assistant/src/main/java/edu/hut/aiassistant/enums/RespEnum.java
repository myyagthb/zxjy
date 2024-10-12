package edu.hut.aiassistant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RespEnum {
    SUCCESS(200,"操作成功"),
    FAIL(500,"操作失败");
    final Integer code;
    final String desc;
}
