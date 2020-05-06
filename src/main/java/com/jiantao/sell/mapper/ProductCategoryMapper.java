package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper {

    void addProductCategory(ProductCategory productCategory);

    List<ProductCategory> getCategoryList();

    List<ProductCategory> getCategoryByTypes(List<Integer> types);
}
