package com.jiantao.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jiantao.sell.entity.OrderDetail;
import com.jiantao.sell.enums.OrderStatusEnum;
import com.jiantao.sell.enums.PayStatusEnum;
import com.jiantao.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:41
 * @description:
 */
@Data
public class OrderDTO {
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private String orderStatus;

    private String payStatus;

    private Date updateTime;

    private Date createTime;

    private List<OrderDetail> orderDetailList;

//    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByStatus(orderStatus, OrderStatusEnum.class);
    }

//    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByStatus(payStatus, PayStatusEnum.class);
    }
}
