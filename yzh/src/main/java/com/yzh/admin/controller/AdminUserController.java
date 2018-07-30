package com.yzh.admin.controller;

import com.yzh.common.base.BaseAdminController;
import com.yzh.common.sys.user.User;
import com.yzh.common.sys.user.UserService;
import com.yzh.common.utils.ResultBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台控制层基类
 *
 * @author yzh
 * @date 2018/4/11 22:18
 */
@Controller
@RequestMapping("${adminPath}/user")
@EnableAutoConfiguration
public class AdminUserController extends BaseAdminController<UserService, User, String> {

    @RequestMapping("add")
    @ResponseBody
    protected ResultBean add(User user, @RequestParam(required = true) String rePassword) {
        return service.add(user, rePassword);
    }

    @RequestMapping("edit")
    protected ResultBean edit(User user, String rePassword) {
        return service.edit(user, rePassword);
    }

}
