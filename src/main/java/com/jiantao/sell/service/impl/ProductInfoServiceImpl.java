package com.jiantao.sell.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiantao.sell.dto.CartDTO;
import com.jiantao.sell.entity.ProductCategory;
import com.jiantao.sell.entity.ProductInfo;
import com.jiantao.sell.enums.ResultEnum;
import com.jiantao.sell.exception.SellException;
import com.jiantao.sell.mapper.ProductCategoryMapper;
import com.jiantao.sell.mapper.ProductInfoMapper;
import com.jiantao.sell.service.ProductCategoryService;
import com.jiantao.sell.service.ProductInfoService;
import com.jiantao.sell.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<ProductInfo> getAllProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> productInfoList = productInfoMapper.getAllProduct();
        PageInfo<ProductInfo> pageInfo = new PageInfo<ProductInfo>(productInfoList);
        return pageInfo;
    }

    @Override
    public void addProduct(ProductInfo productInfo) {
        productInfoMapper.addProduct(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoMapper.getProductById(cartDTO.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoMapper.updateProductInfo(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoMapper.getProductById(cartDTO.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stockResult = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (stockResult < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stockResult);

            productInfoMapper.updateProductInfo(productInfo);
        }
    }
}
