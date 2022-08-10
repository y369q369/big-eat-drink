package com.demo.entity;

import lombok.Data;

/**
 * @Author gs
 * @Date 2022-08-09 14:48
 * @Description 小程序用户
 */
@Data
public class AppUser {

    /**
     * 小程序用户唯一标志
     */
    private String openid;

    /**
     * 会话key
     */
    private String sessionKey;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

}
