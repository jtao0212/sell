package com.jiantao.sell.utils;

import lombok.Synchronized;

import java.util.Random;

/**
 * @author: jiantao
 * @date: 2020-05-07 20:10
 * @description:
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 策略：时间+随机数
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
