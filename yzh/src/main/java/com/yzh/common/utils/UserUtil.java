package com.yzh.common.utils;

import com.yzh.common.sys.user.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yzh
 * @date 2018/4/2 17:32
 */
public class UserUtil {

    public static final String USER_ADMIN = "adminUser";
    public static final String USER_ORDINARY = "ordinaryUser";
    public static final String ADMIN = "admin";

    public static boolean isAdmin(User user) {
        return user.getType() == 0;
    }

    public static User getOrdinaryUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_ORDINARY);
    }

    public static User getAdminUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_ADMIN);
    }

    public static void ordinaryLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        clearCookie(request, response, USER_ORDINARY);
    }

    public static void adminLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        clearCookie(request, response, USER_ADMIN);
    }

    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
