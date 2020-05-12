package com.jiantao.sell.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:12
 * @description:
 */
@Data
public class OrderDetail implements Serializable {
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date updateTime;

    private Date createTime;

}