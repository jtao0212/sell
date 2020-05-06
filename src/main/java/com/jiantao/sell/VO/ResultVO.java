package com.jiantao.sell.VO;

import lombok.Data;

/**
 * @author: jiantao
 * @date: 2020-05-06 21:40
 * @description:
 */
@Data
public class ResultVO<T> {

   private String code;

   private String msg;

   private T data;
}
