package edu.hut.aiassistant.utils;

/**
 * 手机号验证工具类
 */
public class EmailUtil {
    public static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";;
        return email.matches(regex);
    }
}