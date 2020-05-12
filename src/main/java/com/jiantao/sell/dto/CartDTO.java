package com.jiantao.sell.dto;

import lombok.Data;

/**
 * @author: jiantao
 * @date: 2020-05-07 20:20
 * @description:
 */
@Data
public class CartDTO {
    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
