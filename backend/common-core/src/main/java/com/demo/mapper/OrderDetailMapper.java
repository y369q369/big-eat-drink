package com.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单详情表接口
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    /* 批量插入 */
    int batchInsert(@Param("list") List<OrderDetail> orderDetailList);

}