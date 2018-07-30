package com.yzh.admin.service;

import com.yzh.common.sys.user.User;
import com.yzh.common.sys.user.UserDao;
import com.yzh.common.utils.CookieUtil;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yzh
 * @date 2018/4/10 23:14
 */
@Service
@Transactional(readOnly = true)
public class AdminService {

    @Autowired
    private UserDao userDao;

    public ResultBean login(String username, String password, String rememberMe, HttpServletRequest request, HttpServletResponse response) {
        User user = userDao.getByUsername(username);
        if (user == null || !password.equals(user.getPassword()) || user.getType() != 1) {
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "用户名或密码错误");
        }
        //  存session
        request.getSession().setAttribute(UserUtil.USER_ADMIN, user);
        //  更新cookie
        Cookie cookie = CookieUtil.getCookie(request, UserUtil.USER_ADMIN);
        if (StringUtil.notEmpty(rememberMe)) {
            if (cookie == null) {
                cookie = new Cookie(UserUtil.USER_ADMIN, username + "-" + password);
                cookie.setPath("/");
                cookie.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(cookie);
            }
        } else if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return new ResultBean("登录成功");
    }

    public ResultBean logout(HttpServletRequest request, HttpServletResponse response) {
        User user = UserUtil.getAdminUser(request);
        if (user != null)
            UserUtil.adminLogout(request,response);
        return new ResultBean("注销成功");
    }

}
