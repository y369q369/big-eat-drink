package com.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author gs
 * @Date 2022-07-26 19:13
 * @Description 菜单
 */
@Data
@Accessors(chain = true)
public class Menu {

    /**
     * 唯一id
     */
    private String id;

    /**
     * 品类id
     */
    private String catalogId;

    /**
     * 名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 价格（元）
     */
    private BigDecimal price;

    /**
     * 排序
     */
    private int ordinal;

}
