package com.demo.mapper;

import com.demo.dto.OrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gs
 * @Date 2022-08-04 16:20
 * @Description 订单数据处理
 */
public interface OrderDataMapper {

    /**
     * 获取订单详情
     * @param orderId 订单总id
     */
    List<OrderDto> getOrderInfo(@Param("orderId") String orderId);

}
