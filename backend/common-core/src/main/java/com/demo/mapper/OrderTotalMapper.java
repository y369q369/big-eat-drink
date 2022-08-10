package com.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.OrderTotal;

/**
 * 订单表接口
 */
public interface OrderTotalMapper extends BaseMapper<OrderTotal> {

    /**
     * 修改订单状态
     */
    Integer updateOrderStatus(OrderTotal orderTotal);

}