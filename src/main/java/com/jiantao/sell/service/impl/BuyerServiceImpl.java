package com.jiantao.sell.service.impl;

import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.enums.ResultEnum;
import com.jiantao.sell.exception.SellException;
import com.jiantao.sell.service.BuyerService;
import com.jiantao.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jiantao
 * @date: 2020-05-09 21:59
 * @description:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO queryOrderById(String openId, String orderId) {

        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        if (null == orderDTO) {
            log.error("【取消订单】，查不到该订单，orderId = {}", orderDTO);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cacenOrder(orderDTO );
    }

    private OrderDTO checkOrderOwner(String openId, String orderId) {
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if (null == orderDTO) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("【查询订单】，订单openId与用户openId不一致");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;

    }
}
