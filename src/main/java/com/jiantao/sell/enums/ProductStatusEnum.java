package com.jiantao.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP("0", "在架"),
    DOWN("1", "下架");

    private String status;

    private String msg;

    ProductStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
