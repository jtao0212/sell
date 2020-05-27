package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailMapper {

    List<OrderDetail> getDetailByOrderId(@Param("orderId") String orderId);

    void saveOrderDetail(OrderDetail orderDetail);
}
