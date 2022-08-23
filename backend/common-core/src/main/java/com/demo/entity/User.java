package com.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author gs
 * @Date 2022-08-11 16:37
 * @Description 系统用户
 */
@Data
@Accessors(chain = true)
public class User {

    /**
     * 唯一id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别  1：男   2：女
     */
    private Integer sex;

    /**
     * 尝试错误次数
     */
    private Integer tryTimes;

    /**
     * 最近一次登录时间
     */
//    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String lastLogin;

}
