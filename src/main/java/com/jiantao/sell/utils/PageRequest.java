package com.jiantao.sell.utils;

import lombok.Data;

/**
 * @author: jiantao
 * @date: 2020-05-06 21:25
 * @description:
 */
@Data
public class PageRequest {

    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public PageRequest(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
