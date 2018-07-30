package com.yzh.admin.controller;

import com.yzh.admin.service.AdminService;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yzh
 * @date 2018/4/10 23:04
 */
@Controller
@RequestMapping("${adminPath}")
@EnableAutoConfiguration
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("")
    public String admin(HttpServletRequest request) {
        if (UserUtil.getAdminUser(request) != null)
            return "redirect:/a/index";
        return "admin/login";
    }

    @RequestMapping("home")
    public String home() {
        return "redirect:/a/index";
    }

    @RequestMapping("index")
    public String index() {
        return "admin/index";
    }

    @RequestMapping("login")
    @ResponseBody
    public ResultBean login(@RequestParam("username") String username, @RequestParam("password") String password,
                            String rememberMe, HttpServletRequest request, HttpServletResponse response) {
        return adminService.login(username, password, rememberMe, request, response);
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        adminService.logout(request, response);
        return "redirect:/a";
    }

}
