package com.demo.dto;

import lombok.Data;

/**
 * @Author gs
 * @Date 2022-08-18 10:48
 * @Description token创建对象
 */
@Data
public class TokenDto {

    /**
     * token对应key
     */
    private String name;

    /**
     * token类型
     */
    private String type;

    /**
     * 过期天数
     */
    private int expireDay;

}
