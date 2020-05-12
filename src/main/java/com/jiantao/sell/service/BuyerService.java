package com.jiantao.sell.service;

import com.jiantao.sell.dto.OrderDTO;

public interface BuyerService {
    //查询订单
    OrderDTO queryOrderById(String openId, String orderId);

    OrderDTO cancelOrder(String openId, String orderId);
}
