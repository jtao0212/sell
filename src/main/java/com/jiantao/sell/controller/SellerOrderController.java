package com.jiantao.sell.controller;

import com.github.pagehelper.PageInfo;
import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.entity.ProductInfo;
import com.jiantao.sell.service.OrderService;
import com.jiantao.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author: jiantao
 * @date: 2020-05-09 23:24
 * @description:
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             Map<String, Object> map) {
        PageInfo<OrderDTO> orderDTOPage = orderService.getOrderList(pageNum, pageSize);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", orderDTOPage.getPageNum());
        map.put("size", orderDTOPage.getPageSize());
        return new ModelAndView("/order/list", map);
    }
}
