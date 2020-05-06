package com.jiantao.sell.utils;

import com.jiantao.sell.VO.ResultVO;

/**
 * @author: jiantao
 * @date: 2020-05-06 22:10
 * @description:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
       return success(null);
    }

    public static ResultVO error(String code ,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
