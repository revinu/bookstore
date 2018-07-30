package com.yzh.common.sys.dict;

import com.yzh.common.base.BaseAdminController;
import com.yzh.common.utils.DictUtil;
import com.yzh.common.utils.ResultBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 字典表控制层
 *
 * @author yzh
 * @date 2018/4/12 12:18
 */
@Controller
@RequestMapping("${adminPath}/d")
@EnableAutoConfiguration
public class AdminDictController extends BaseAdminController<DictService, Dict, String> {

    @RequestMapping("${category}")
    public String listByType(Dict dict, Model model) {
        dict.setType(DictUtil.TYPE_CATEGORY);
        List<Dict> list = service.listByEntity(dict);
        model.addAttribute("list", list);
        return "admin/dict/categoryList";
    }

    @RequestMapping("${category}/add")
    @ResponseBody
    public ResultBean addCategory(@RequestParam(required = true) Integer key, @RequestParam(required = true) String value) {
        return service.addCategory(key, value);
    }

    @RequestMapping("${category}/edit")
    @ResponseBody
    public ResultBean editCategory(@RequestParam(required = true) String id, @RequestParam(required = true) String value) {
        return service.editCategory(id, value);
    }

    @RequestMapping("${category}/remove")
    @ResponseBody
    public ResultBean removeCategory(@RequestParam(required = true) String id) {
        return service.deleteCategory(id);
    }

}
