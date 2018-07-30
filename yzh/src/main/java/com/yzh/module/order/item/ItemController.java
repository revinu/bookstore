package com.yzh.module.order.item;

import com.yzh.common.base.BaseController;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.UserUtil;
import com.yzh.module.order.linkinfo.LinkInfo;
import com.yzh.module.order.linkinfo.LinkInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/20 13:46
 */
@Controller
@RequestMapping("item")
@EnableAutoConfiguration
public class ItemController extends BaseController<ItemService, Item, String> {

    @Autowired
    private LinkInfoService linkInfoService;

    @RequestMapping("addToCart")
    @ResponseBody
    public ResultBean addToCart(@RequestParam(required = true) String bookId, @RequestParam(required = true) int num
            , HttpServletRequest request) {
        return service.addToCart(bookId, num, request);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultBean delete(@RequestParam(required = true) String id, HttpServletRequest request) {
        return service.remove(id, request);
    }


    @RequestMapping("cart")
    public String cart(Item item, Model model, HttpServletRequest request) {
        List<Item> list = service.cart(item, request);
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setUserId(UserUtil.getOrdinaryUser(request).getId());
        List<LinkInfo> linkInfoList = linkInfoService.listByEntity(linkInfo);
        model.addAttribute("list", list);
        model.addAttribute("linkInfoList", linkInfoList);
        return "module/item/cart";
    }

}
