package com.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author gs
 * @Date 2022-08-04 16:20
 * @Description 订单明细
 */
@Data
public class OrderDto {

    // 订单总id
    private String orderId;

    // 订单状态 1: 创建  2：待付款  3：已付款  4：已退款  5：完成
    private Integer status;

    // 订单分id
    private String detailId;

    // 菜品购买数量
    private Integer number;

    // 菜品id
    private String menuId;

    // 种类id
    private String catalogId;

    // 菜品名称
    private String NAME;

    // 菜品英文名
    private String nameEn;

    // 菜品价格
    private BigDecimal price;

    // 菜品排序
    private String ordinal;

}
