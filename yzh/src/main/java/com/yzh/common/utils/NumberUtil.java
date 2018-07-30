package com.yzh.common.utils;

/**
 * 数字类工具类
 *
 * @author yzh
 * @date 2018/4/10 23:22
 */
public class NumberUtil {

    /**
     * 比较两个Integer数是否相等
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean equals(Integer num1, Integer num2) {
        if (num1 == null || num2 == null) {
            return false;
        }
        return num1.compareTo(num2) == 0;
    }

}
