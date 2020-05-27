package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.OrderMaster;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderMasterMapper {

    List<OrderMaster> getOrderByBuyerOpenId(String buyerOpenId);

    void saveOrderMaster(OrderMaster orderMaster);

    OrderMaster getOrderById(@Param("orderId") String orderId);

    int updateOrderStatus(@Param("orderMaster") OrderMaster orderMaster);

    List<OrderMaster> getOrderList();
}
