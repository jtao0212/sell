package com.jiantao.sell.enums;

import lombok.Getter;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:09
 * @description:
 */
@Getter
public enum PayStatusEnum implements StatusEnum {
    WAIT("0", "待支付"),

    SUCCESS("1", "支付成功");

    private String status;

    private String msg;

    PayStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
