package com.yzh.module.order;

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
 * @date 2018/4/20 13:48
 */
@Controller
@RequestMapping("order")
@EnableAutoConfiguration
public class OrderController extends BaseController<OrderService, Order, String> {

    @RequestMapping("place")
    @ResponseBody
    public ResultBean place(@RequestParam(required = true) String linkinfoId,@RequestParam(required = true)String itemIds, HttpServletRequest request) {
        return service.place(linkinfoId, itemIds, request);
    }

}
