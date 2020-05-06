package com.jiantao.sell.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: jiantao
 * @date: 2020-05-06 20:48
 * @description: 商品
 */
@Data
public class ProductInfo {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private String categoryStatus;

    private String categoryType;

    private Date updateTime;

    private Date createTime;
}
