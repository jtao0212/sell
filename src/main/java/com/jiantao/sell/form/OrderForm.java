package com.jiantao.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: jiantao
 * @date: 2020-05-08 21:23
 * @description:
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openId不能为空")
    private String openId;

    @NotEmpty(message = "购物车不能为空")
    private String items;


}
