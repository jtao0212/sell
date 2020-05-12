package com.jiantao.sell.service;


import com.github.pagehelper.PageInfo;
import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.utils.PageRequest;


public interface OrderService {

    /*创建订单*/
    OrderDTO createOrder(OrderDTO orderDTO);

    /*查询单个订单*/
    OrderDTO getOrderById(String orderId);

    /*查询订单列表*/
    PageInfo<OrderDTO> getOrderByBuyerOpenId(String buyerOpenId, PageRequest pageRequest);

    /*取消订单*/
    OrderDTO cacenOrder(OrderDTO orderDTO);

    /*完成订单*/
    OrderDTO finishOrder(OrderDTO orderDTO);

    /*支付订单*/
    OrderDTO payOrder(OrderDTO orderDTO);

    PageInfo<OrderDTO> getOrderList(Integer pageNum,Integer pageSize);
}
