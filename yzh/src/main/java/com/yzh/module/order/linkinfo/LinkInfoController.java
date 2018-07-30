package com.yzh.module.order.linkinfo;

import com.yzh.common.base.BaseController;
import com.yzh.common.utils.ResultBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yzh
 * @date 2018/4/22 8:29
 */
@Controller
@RequestMapping("linkInfo")
@EnableAutoConfiguration
public class LinkInfoController extends BaseController<LinkInfoService, LinkInfo, String> {

    @RequestMapping("delete")
    @ResponseBody
    public ResultBean delete(@RequestParam(required = true) String id, HttpServletRequest request) {
        return service.remove(id, request);
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultBean add(LinkInfo linkInfo, HttpServletRequest request) {
        return service.add(linkInfo, request);
    }

    @RequestMapping("edit")
    @ResponseBody
    public ResultBean edit(LinkInfo linkInfo, HttpServletRequest request) {
        return service.edit(linkInfo, request);
    }

}
