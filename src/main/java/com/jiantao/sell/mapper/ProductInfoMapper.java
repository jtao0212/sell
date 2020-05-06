package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoMapper {

    List<ProductInfo> getProductByStatus(@Param("status") String status);

    ProductInfo getProductById(String id);

    List<ProductInfo> getAllProduct();

    void addProduct(ProductInfo productInfo);
}
