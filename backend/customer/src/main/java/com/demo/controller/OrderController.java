package com.demo.controller;

import com.demo.request.SaveOrderRequest;
import com.demo.response.ResponseResult;
import com.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author gs
 * @Date 2022-08-02 16:58
 * @Description 订单控制层
 */
@RestController
@RequestMapping("order")
@Api(tags = "订单")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "新增订单")
    @PostMapping("order")
    public ResponseResult order(@RequestBody SaveOrderRequest saveOrderRequest) {
        return orderService.saveOrder(saveOrderRequest);
    }

    @ApiOperation(value = "查询订单详情")
    @GetMapping("orderInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单总id", example = "12345", dataType = "String", paramType = "query", required = true),
    })
    public ResponseResult orderInfo(@RequestParam("orderId") String orderId) {
        return orderService.getOrderInfo(orderId);
    }

}
