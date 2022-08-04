package com.demo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * order_detail 订单详情表
 * @author gs
 */
@Data
@Accessors(chain = true)
public class OrderDetail implements Serializable {
    /**
     * 分订单id
     */
    private String detailId;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}