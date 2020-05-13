package com.jiantao.sell.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiantao.sell.converter.OrderMaster2OrderDTOConverter;
import com.jiantao.sell.dto.CartDTO;
import com.jiantao.sell.dto.OrderDTO;
import com.jiantao.sell.entity.OrderDetail;
import com.jiantao.sell.entity.OrderMaster;
import com.jiantao.sell.entity.ProductInfo;
import com.jiantao.sell.enums.OrderStatusEnum;
import com.jiantao.sell.enums.PayStatusEnum;
import com.jiantao.sell.enums.ResultEnum;
import com.jiantao.sell.exception.SellException;
import com.jiantao.sell.mapper.OrderDetailMapper;
import com.jiantao.sell.mapper.OrderMasterMapper;
import com.jiantao.sell.service.OrderService;
import com.jiantao.sell.service.ProductInfoService;
import com.jiantao.sell.utils.KeyUtil;
import com.jiantao.sell.utils.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: jiantao
 * @date: 2020-04-29 21:41
 * @description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        // 1、查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.getProductById(orderDetail.getProductId());
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 2、计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailMapper.saveOrderDetail(orderDetail);
        }

        //3、写入数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getStatus());

        orderMasterMapper.saveOrderMaster(orderMaster);

        //4、减少库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOS);

        return orderDTO;
    }

    @Override
    public OrderDTO getOrderById(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.getOrderById(orderId);
        if (null == orderMaster) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailMapper.getDetailByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> getOrderByBuyerOpenId(String buyerOpenId, PageRequest pageRequest) {

        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<OrderMaster> orderMasterList = orderMasterMapper.getOrderByBuyerOpenId(buyerOpenId);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasterList);
        List<OrderDTO> orderDTOS = OrderMaster2OrderDTOConverter.convert(pageInfo.getList());
        return new PageInfo<>(orderDTOS);
    }

    @Override
    @Transactional
    public OrderDTO cacenOrder(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        // 查询订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())) {
            log.error("【取消订单】订单状态不正确，orderId = {}，orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CACEL.getStatus());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int change = orderMasterMapper.updateOrderStatus(orderMaster);
        if (change < 1) {
            log.error("【取消订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //返回库存
        List<CartDTO> cartDTOS = orderDTO.getOrderDetailList().stream()
                .map(
                        e -> new CartDTO(e.getProductId(), e.getProductQuantity())
                ).collect(Collectors.toList());

        productInfoService.increaseStock(cartDTOS);

        //如果已经支付需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getStatus())) {
            // TODO: 2020-05-08
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finishOrder(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())) {
            log.error("【完结订单】订单状态不正确，orderId = {},orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getStatus());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int change = orderMasterMapper.updateOrderStatus(orderMaster);
        if (change < 1) {
            log.error("【完结订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO payOrder(OrderDTO orderDTO) {

        //1、判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())) {
            log.error("【支付订单】订单状态不正确，orderId = {},orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2、判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT)) {
            log.error("【支付订单】支付状态不正确，orderId = {},payStatus = {}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getStatus());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        int change = orderMasterMapper.updateOrderStatus(orderMaster);
        if (change < 1) {
            log.error("【支付订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    @Override
    public PageInfo<OrderDTO> getOrderList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderMaster> orderMasters = orderMasterMapper.getOrderList();
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(orderMasters);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(pageInfo.getList());
        PageInfo<OrderDTO> orderDTOPageInfo = new PageInfo<>(orderDTOList);
        orderDTOPageInfo.setPages(pageInfo.getPages());
        return orderDTOPageInfo;
    }
}
