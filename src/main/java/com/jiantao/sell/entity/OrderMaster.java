package com.jiantao.sell.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:02
 * @description:
 */
@Data
public class OrderMaster {

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
}