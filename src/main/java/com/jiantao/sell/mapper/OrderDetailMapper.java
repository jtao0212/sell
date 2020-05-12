package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailMapper {

    List<OrderDetail> getDetailByOrderId(String orderId);

    void  saveOrderDetail(OrderDetail orderDetail);
}
