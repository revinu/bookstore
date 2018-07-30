package com.yzh.common.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean hasNull(String... params) {
        if (params.length == 0) {
            return true;
        }
        for (String param : params) {
            if (param == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasEmpty(String... params) {
        if (params.length == 0) {
            return true;
        }
        for (String param : params) {
            if (empty(param)) {
                return true;
            }
        }
        return false;
    }

    public static boolean empty(String param) {
        return param == null || "".equals(param);
    }

    public static boolean notEmpty(String param) {
        return !empty(param);
    }

    /**
     * 正则替换
     *
     * @param str        原字符串
     * @param regex      正则表达式
     * @param replaceStr 替换正则的字符串
     * @param ignoreCase 是否忽略大小写(不传为true)
     * @return
     */
    public static String replace(String str, String regex, String replaceStr, boolean ignoreCase) {
        if (hasNull(str, regex, replaceStr)) {
            return str;
        }
        Pattern pattern;
        if (ignoreCase) {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regex);
        }
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, replaceStr);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String replace(String str, String regex, String replaceStr) {
        return replace(str, regex, replaceStr, true);
    }

}
