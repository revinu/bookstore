package com.yzh.module.index;

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
 * @date 2018/4/2 16:34
 */
@Service
@Transactional(readOnly = true)
public class IndexService {

    @Autowired
    private UserDao userDao;

    /**
     * 普通用户登录
     *
     * @param username
     * @param password
     * @param rememberMe
     * @param request
     * @param response
     * @return
     */
    public ResultBean login(String username, String password, String rememberMe,
                            HttpServletRequest request, HttpServletResponse response) {
        User user = userDao.getByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "用户名或密码错误");
        }
        //  存session
        request.getSession().setAttribute(UserUtil.USER_ORDINARY, user);
        //  更新cookie
        Cookie cookie = CookieUtil.getCookie(request, UserUtil.USER_ORDINARY);
        if (StringUtil.notEmpty(rememberMe)) {
            if (cookie == null) {
                cookie = new Cookie(UserUtil.USER_ORDINARY, username + "-" + password);
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

    /**
     * 注销
     *
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        UserUtil.ordinaryLogout(request, response);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param rePassword
     * @param request
     * @param response
     * @return
     */
    @Transactional(readOnly = false)
    public ResultBean register(String username, String password, String rePassword, HttpServletRequest request, HttpServletResponse response) {
        User user = userDao.getByUsername(username);
        if (user != null)
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "该用户名已被注册");
        if (!password.equals(rePassword))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "两次密码不一致");
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userDao.insert(user);
        return login(username, password, "", request, response);
    }

    /**
     * 重置密码
     *
     * @param oldPassword
     * @param password
     * @param rePassword
     * @param request
     * @param response
     * @return
     */
    @Transactional(readOnly = false)
    public ResultBean reset(String oldPassword, String password, String rePassword, HttpServletRequest request, HttpServletResponse response) {
        if (!password.equals(rePassword))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "两次密码不一致");
        User onlineUser = UserUtil.getOrdinaryUser(request);
        if (onlineUser == null)
            return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN);
        if (!oldPassword.equals(onlineUser.getPassword()))
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "原始密码错误");
        if (!oldPassword.equals(password)) {
            onlineUser.setPassword(password);
            userDao.resetPassword(onlineUser.getId(), password);
        }
        UserUtil.ordinaryLogout(request, response);
        return new ResultBean("修改成功");
    }

}
