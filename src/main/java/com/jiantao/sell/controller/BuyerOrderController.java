package com.jiantao.sell.controller;

import com.github.pagehelper.PageInfo;
import com.jiantao.sell.VO.ResultVO;
import com.jiantao.sell.converter.OrderForm2OrderDTOConverter;
import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.enums.ResultEnum;
import com.jiantao.sell.exception.SellException;
import com.jiantao.sell.form.OrderForm;
import com.jiantao.sell.service.BuyerService;
import com.jiantao.sell.service.OrderService;
import com.jiantao.sell.utils.PageRequest;
import com.jiantao.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jiantao
 * @date: 2020-05-08 21:18
 * @description:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CARTS_EMPTY);
        }
        orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer pageNum,
                                         @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单列表】，openId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        PageInfo<OrderDTO> orderDTOPageInfo = orderService.getOrderByBuyerOpenId(openId, pageRequest);
        return ResultVOUtil.success(orderDTOPageInfo.getList());
    }

    //订单详情

    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.queryOrderById(openId, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId) {

        buyerService.cancelOrder(openId, orderId);
        return ResultVOUtil.success();
    }
}
