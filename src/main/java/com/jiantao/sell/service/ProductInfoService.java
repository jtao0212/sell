package com.jiantao.sell.service;

import com.jiantao.sell.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    List<ProductInfo> getProductByStatus(String status);

    ProductInfo getProductById(String id);

    List<ProductInfo> getAllProduct();

    void addProduct(ProductInfo productInfo);
}
