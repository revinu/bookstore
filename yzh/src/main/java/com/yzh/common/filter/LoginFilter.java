package com.yzh.common.filter;

import com.yzh.common.utils.UserUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台过滤器
 *
 * @author yzh
 * @date 2018/3/18 15:54
 */
@Component
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "loginFilter")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        if (uri.startsWith(contextPath + "/login") || uri.startsWith(contextPath + "/register")
                || uri.startsWith(contextPath + "/logout") || uri.startsWith(contextPath + "/a/login")
                || uri.startsWith(contextPath + "/a/logout") || uri.equals(contextPath + "/")
                || uri.startsWith(contextPath + "/index") || uri.equals(contextPath + "/a")
                || uri.startsWith(contextPath + "/static") || uri.startsWith(contextPath + "/image")
                || uri.startsWith(contextPath + "/frame") || uri.startsWith(contextPath + "/book/detail")
                || uri.startsWith(contextPath + "/book/category") || uri.equals(contextPath + "/search")) {
            chain.doFilter(req, res);
            return;
        }
        if (uri.startsWith(contextPath + "/a")) {
            if ((req.getSession().getAttribute(UserUtil.USER_ADMIN) == null)) {
                res.sendRedirect(contextPath + "/a");
            } else {
                chain.doFilter(req, res);
            }
            return;
        }
        if (req.getSession().getAttribute(UserUtil.USER_ORDINARY) == null) {
            res.sendRedirect(contextPath);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

}
