package com.yzh.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验
 *
 * @author yzh
 * @date 2018/1/29 17:48
 */
public class ValidateUtil {

    public static boolean check(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);
        return m.matches();
    }

    /**
     * 校验手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        return check(phone, "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return check(email, "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    }

    /**
     * 校验邮编
     *
     * @param zipCode
     * @return
     */
    public static boolean checkZipCode(String zipCode) {
        return check(zipCode, "\\d{6}");
    }

}
