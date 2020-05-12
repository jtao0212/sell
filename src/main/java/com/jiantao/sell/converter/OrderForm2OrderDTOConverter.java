package com.jiantao.sell.converter;

import com.alibaba.fastjson.JSON;
import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.entity.OrderDetail;
import com.jiantao.sell.enums.ResultEnum;
import com.jiantao.sell.exception.SellException;
import com.jiantao.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-05-08 21:38
 * @description:
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = JSON.parseArray(orderForm.getItems(), OrderDetail.class);

        } catch (Exception e) {
            log.error("【对象转换】错误，String = {}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
