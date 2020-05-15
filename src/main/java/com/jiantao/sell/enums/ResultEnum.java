package com.jiantao.sell.enums;

import lombok.Getter;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:55
 * @description:
 */
@Getter
public enum ResultEnum {

    SUCCESS("0", "成功"),

    PARAM_ERROR("1", "参数不正确"),

    PRODUCT_NOT_EXIST("10", "商品不存在"),

    PRODUCT_STOCK_ERROR("11", "商品库存不足"),

    ORDER_NOT_EXIST("12", "订单不存在"),

    ORDER_DETAIL_NOT_EXIST("13", "订单明细不存在"),

    ORDER_STATUS_ERROR("14", "订单状态不正确"),

    ORDER_UPDATE_ERROR("15", "订单更新失败"),

    PAY_STATUS_ERROR("16", "支付状态不正确"),

    CARTS_EMPTY("17", "购物车为空"),

    ORDER_OWNER_ERROR("18", "该订单不属于当前用户"),

    ORDER_CANCEL_SUCCESS("19", "订单取消成功"),
    ;

    private String code;

    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
