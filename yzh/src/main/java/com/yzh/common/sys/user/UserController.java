package com.yzh.common.sys.user;

import com.yzh.common.base.BaseController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yzh
 * @date 2018/4/2 10:43
 */
@Controller
@RequestMapping("user")
@EnableAutoConfiguration
public class UserController extends BaseController<UserService, User, String> {

}
