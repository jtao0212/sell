package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.OrderMaster;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderMasterMapper {

    List<OrderMaster> getOrderByBuyerOpenId(String buyerOpenId);

    void saveOrderMaster(OrderMaster orderMaster);

    OrderMaster getOrderById(String orderId);

    int updateOrderStatus(OrderMaster orderMaster);

    List<OrderMaster> getOrderList();
}
