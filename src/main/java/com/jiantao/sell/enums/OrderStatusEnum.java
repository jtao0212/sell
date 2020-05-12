package com.jiantao.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements StatusEnum {
    NEW("0", "新订单"),
    FINISHED("1", "订单完成"),
    CACEL("2", "订单取消");

    private String status;

    private String msg;

    OrderStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
