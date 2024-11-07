package edu.hut.aiassistant.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    STUDENT(0,"学生"),
    TEACHER(1,"教师");
    final Integer roleCode;//角色代码
    final String roleDesc;//角色描述
}
