package com.jiantao.sell.utils;

import lombok.Data;

import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-05-06 21:27
 * @description:
 */
@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;
}
