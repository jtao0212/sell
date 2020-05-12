package com.jiantao.sell.exception;

import com.jiantao.sell.enums.ResultEnum;

/**
 * @author: jiantao
 * @date: 2020-05-07 19:55
 * @description:
 */
public class SellException extends RuntimeException {

    private String code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
