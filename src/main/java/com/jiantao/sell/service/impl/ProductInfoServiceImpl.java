package com.jiantao.sell.service.impl;

import com.jiantao.sell.entity.ProductCategory;
import com.jiantao.sell.entity.ProductInfo;
import com.jiantao.sell.mapper.ProductCategoryMapper;
import com.jiantao.sell.mapper.ProductInfoMapper;
import com.jiantao.sell.service.ProductCategoryService;
import com.jiantao.sell.service.ProductInfoService;
import com.jiantao.sell.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-04-29 21:41
 * @description:
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getProductByStatus(String status) {
        return productInfoMapper.getProductByStatus(status);
    }

    @Override
    public ProductInfo getProductById(String id) {
        return productInfoMapper.getProductById(id);
    }

    @Override
    public List<ProductInfo> getAllProduct() {
        return productInfoMapper.getAllProduct();
    }

    @Override
    public void addProduct(ProductInfo productInfo) {
        productInfoMapper.addProduct(productInfo);
    }
}
