package com.demo.enums;

/**
 * @Author gs
 * @Date 2022-08-18 11:14
 * @Description token类型枚举
 */
public interface TokenEnum {
    // token类型
    String TYPE_LOGIN = "login";              // 登录
    String TYPE_PERMANENT = "permanent";      // 长期

    // 时效
    int LOGIN_EXPIRE = 7;                     // 登录
}
