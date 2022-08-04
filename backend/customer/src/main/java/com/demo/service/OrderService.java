package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.dto.OrderDto;
import com.demo.entity.Menu;
import com.demo.entity.OrderTotal;
import com.demo.entity.OrderDetail;
import com.demo.mapper.MenuMapper;
import com.demo.mapper.OrderDataMapper;
import com.demo.mapper.OrderDetailMapper;
import com.demo.mapper.OrderTotalMapper;
import com.demo.request.SaveOrderRequest;
import com.demo.response.ResponseResult;
import com.demo.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author gs
 * @Date 2022-08-02 17:05
 * @Description 订单业务
 */
@Service
@Slf4j
public class OrderService {

    @Resource
    private OrderTotalMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private OrderDataMapper orderDataMapper;

    /**
     * 新增订单
     */
    public ResponseResult saveOrder(SaveOrderRequest saveOrderRequest) {
        String orderId = StringUtils.hasLength(saveOrderRequest.getOrderId()) ? saveOrderRequest.getOrderId() : CommonUtil.randomUUID();
        String detailId = CommonUtil.randomUUID();

        List<Menu> menuList = menuMapper.selectList(null);
        Map<String, BigDecimal> menuMap = menuList.stream().collect(Collectors.toMap(Menu :: getId, Menu :: getPrice, (a, b) -> a));

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (Map.Entry<String, Integer> detail : saveOrderRequest.getDetailMap().entrySet()) {
            String menuId = detail.getKey();
            Integer number = detail.getValue();

            BigDecimal price = menuMap.get(menuId);
            if (price != null) {
                OrderDetail orderDetail = new OrderDetail()
                        .setDetailId(detailId)
                        .setMenuId(menuId)
                        .setNumber(number);
                orderDetailList.add(orderDetail);

                totalPrice = totalPrice.add(price.multiply(new BigDecimal(number)));
            }
        }

        if (orderDetailList.size() > 0) {
            int batchInsertNum = orderDetailMapper.batchInsert(orderDetailList);

            OrderTotal orderTotal = new OrderTotal();
            orderTotal.setId(orderId)
                    .setUserId(saveOrderRequest.getUserId())
                    .setMoney(totalPrice)
                    .setDetailId(detailId);

            int insertNum = orderMapper.insert(orderTotal);
            log.info("【新增订单】 插入订单总表 {} 条， 订单分表 {} 条", insertNum, batchInsertNum);

            JSONObject data = new JSONObject();
            data.put("id", orderId);
            data.put("msg", "新增成功");
            return ResponseResult.success(data);
        } else {
            return ResponseResult.fail("菜单异常");
        }
    }

    /**
     * 查询订单
     * @param orderId 订单总id
     */
    public ResponseResult getOrderInfo(String orderId) {
        List<OrderDto> orderDtoList = orderDataMapper.getOrderInfo(orderId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderDto orderDto : orderDtoList) {
            totalPrice = totalPrice.add(orderDto.getPrice().multiply(BigDecimal.valueOf(orderDto.getNumber())));
        }
        JSONObject data = new JSONObject();
        data.put("orderList", orderDtoList);
        data.put("totalPrice", totalPrice);
        return ResponseResult.success(data);
    }

}
