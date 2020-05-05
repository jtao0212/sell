package com.jiantao.sell.service.impl;

import com.jiantao.sell.entity.ProductCategory;
import com.jiantao.sell.mapper.ProductCategoryMapper;
import com.jiantao.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-04-29 21:41
 * @description:
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryMapper.addProductCategory(productCategory);
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return productCategoryMapper.getCategoryList();
    }

    @Override
    public List<ProductCategory> getCategoryByType(int categoryType) {
        return productCategoryMapper.getCategoryByType(categoryType);
    }
}
