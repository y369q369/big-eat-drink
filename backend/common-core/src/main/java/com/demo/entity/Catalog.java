package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author gs
 * @Date 2022-07-26 19:11
 * @Description 品类
 */
@Data
@Accessors(chain = true)
public class Catalog {

    /**
     * 唯一id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * '排序'
     */
    private int ordinal;

}
