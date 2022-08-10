package com.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * order 订单表
 * @author gs
 */
@Data
@Accessors(chain = true)
public class OrderTotal implements Serializable {

    /**
     * 唯一id，总订单编号
     */
    private String id;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 顾客id
     */
    private String userId;

    /**
     * 分订单 编号
     */
    private String detailId;

    /**
     * 订单状态 1: 创建  2：待付款  3：已付款  4：已退款  5：完成
     */
    private Integer status;

    /**
     * 创建事件
     */
    @TableField(exist = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(exist = false)
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}