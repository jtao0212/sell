package com.jiantao.sell.utils;

import com.jiantao.sell.enums.StatusEnum;

/**
 * @author: jiantao
 * @date: 2020-05-12 22:53
 * @description:
 */
public class EnumUtil {
    public static <T extends StatusEnum> T getByStatus(String status, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (status.equals(each.getStatus())) {
                return each;
            }
        }
        return null;
    }
}
