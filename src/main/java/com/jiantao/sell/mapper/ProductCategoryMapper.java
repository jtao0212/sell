package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper {

    void addProductCategory(ProductCategory productCategory);

    List<ProductCategory> getCategoryList();

    List<ProductCategory> getCategoryByType(@Param(value = "categoryType") int categoryType);
}
