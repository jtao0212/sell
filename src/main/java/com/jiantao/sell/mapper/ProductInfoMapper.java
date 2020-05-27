package com.jiantao.sell.mapper;

import com.jiantao.sell.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoMapper {

    List<ProductInfo> getProductByStatus(@Param("status") String status);

    ProductInfo getProductById(@Param("productId") String productId);

    List<ProductInfo> getAllProduct();

    void addProduct(ProductInfo productInfo);

    void updateProductInfo(@Param("productInfo") ProductInfo productInfo);
}
