package com.jiantao.sell.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: jiantao
 * @date: 2020-04-29 20:18
 * @description: 产品类别实体
 */
@Data
public class ProductCategory {

    private Integer categoryId;

    private String categoryName;

    private String categoryType;

    private Date updateTime;

    private Date createTime;


    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType='" + categoryType + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
