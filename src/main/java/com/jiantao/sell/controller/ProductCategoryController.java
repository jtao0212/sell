package com.jiantao.sell.controller;

import com.jiantao.sell.entity.ProductCategory;
import com.jiantao.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: jiantao
 * @date: 2020-04-29 21:32
 * @description:
 */
@RestController
@RequestMapping(value = "/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping(value = "/addCategory")
    public void addProductCategory(@RequestBody ProductCategory productCategory) {

        productCategoryService.addProductCategory(productCategory);
    }

    @GetMapping("/getCategoryList")
    public List<ProductCategory> getCategoryList() {
        return productCategoryService.getCategoryList();
    }

    @PostMapping(value = "/getCategoryByTypes")
    public List<ProductCategory> getCategoryByTypes(@RequestBody List<Integer> types){
        return productCategoryService.getCategoryByTypes(types);

    }
}
