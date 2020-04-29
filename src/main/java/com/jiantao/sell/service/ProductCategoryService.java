package com.jiantao.sell.service;

import com.jiantao.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {


    void  addProductCategory(ProductCategory productCategory);

    List<ProductCategory> getList();
}
