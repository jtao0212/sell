package com.jiantao.sell.service;

import com.github.pagehelper.PageInfo;
import com.jiantao.sell.dto.CartDTO;
import com.jiantao.sell.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> getProductByStatus(String status);

    ProductInfo getProductById(String id);

    PageInfo<ProductInfo> getAllProduct(int pageNum, int pageSize);

    void addProduct(ProductInfo productInfo);

    /*增加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /*减少库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

}
