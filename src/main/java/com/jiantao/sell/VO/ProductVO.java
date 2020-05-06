package com.jiantao.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-05-06 21:43
 * @description:
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private String categoryType;

    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;
}
