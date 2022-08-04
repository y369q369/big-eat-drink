package com.demo.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author gs
 * @Date 2022-08-02 17:11
 * @Description 保存订单请求体
 */
@Data
public class SaveOrderRequest {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 选择清单
     */
    private Map<String, Integer> detailMap;

}
