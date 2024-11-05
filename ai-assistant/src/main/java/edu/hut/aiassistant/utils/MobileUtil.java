package edu.hut.aiassistant.utils;

/**
 * 手机号验证工具类
 */
public class MobileUtil {
    public static boolean isValidMobile(String mobile) {
        String regex = "^1[3456789]\\d{9}$";
        return mobile.matches(regex);
    }
}