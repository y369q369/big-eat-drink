package com.demo.entity;

import lombok.Data;

/**
 * @Author gs
 * @Date 2022-08-08 15:12
 * @Description 系统信息
 */
@Data
public class SystemInfo {

    /**
     * 字段
     */
    private String field;

    /**
     * 字段对应值
     */
    private String data;

    /**
     * 字段说明
     */
    private String instruction;

}
