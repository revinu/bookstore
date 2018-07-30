package com.yzh.common.frame;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * frame映射器
 *
 * @author yzh
 * @date 2018/4/11 0:19
 */
@Controller
@RequestMapping("frame")
@EnableAutoConfiguration
public class FrameController {

    @RequestMapping("top")
    public String adminTop() {
        return "admin/top";
    }

    @RequestMapping("bottom")
    public String adminBottom() {
        return "admin/bottom";
    }

    @RequestMapping("left")
    public String adminLeft() {
        return "admin/left";
    }

    @RequestMapping("welcome")
    public String adminWelcome() {
        return "admin/welcome";
    }

}
