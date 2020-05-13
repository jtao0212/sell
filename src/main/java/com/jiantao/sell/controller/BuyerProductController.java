package com.jiantao.sell.controller;

import com.github.pagehelper.PageInfo;
import com.jiantao.sell.VO.ProductInfoVO;
import com.jiantao.sell.VO.ProductVO;
import com.jiantao.sell.VO.ResultVO;
import com.jiantao.sell.entity.ProductCategory;
import com.jiantao.sell.entity.ProductInfo;
import com.jiantao.sell.enums.ProductStatusEnum;
import com.jiantao.sell.service.ProductCategoryService;
import com.jiantao.sell.service.ProductInfoService;
import com.jiantao.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: jiantao
 * @date: 2020-04-29 21:32
 * @description: 买家商品
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //1、查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.getProductByStatus(ProductStatusEnum.UP.getStatus());

        //2、查询类目
        List<String> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.getCategoryByTypes(categoryTypeList);

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }


    @PostMapping(value = "/addProduct")
    public void addProduct(@RequestBody ProductInfo productInfo) {

        productInfoService.addProduct(productInfo);
    }

    @GetMapping("/getAllProduct")
    public PageInfo<ProductInfo> getAllProduct(@RequestParam(value = "pageNum") Integer pageNum,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        return productInfoService.getAllProduct(pageNum, pageSize);
    }

    @PostMapping(value = "/getProductById")
    public ProductInfo getProductById(@RequestParam String id) {
        return productInfoService.getProductById(id);
    }

    @PostMapping("/getProductByStatus")
    public List<ProductInfo> getProductByStatus(@RequestParam String status) {
        return productInfoService.getProductByStatus(status);
    }
}
