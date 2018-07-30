package com.yzh.common.base;

import com.yzh.common.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 控制层基类
 *
 * @author yzh
 * @date 2018/4/2 10:43
 */
@EnableAutoConfiguration
public abstract class BaseController<S extends BaseService, E extends BaseEntity, I> {

    @Autowired
    protected S service;

    protected String entitySimpleName;    //  实体类全小写类名,用在返回视图
    protected String viewPreview;         //  返回视图的path + jsp前缀

    protected void initEntitySimpleName() {
        ParameterizedType controllerType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] cTypeArray = controllerType.getActualTypeArguments();
        Class entityClass = (Class) cTypeArray[1];
        entitySimpleName = entityClass.getSimpleName().toLowerCase();
    }

    public BaseController() {
        initEntitySimpleName();
        viewPreview = "module/" + entitySimpleName + "/" + entitySimpleName;
    }

    @RequestMapping({"", "list"})
    protected String list(E entity, Model model) {
        List list = service.listByEntity(entity);
        model.addAttribute("list", list);
        return viewPreview + "List";
    }

    @RequestMapping("addForm")
    protected String addForm() {
        return viewPreview + "AddForm";
    }

    /*@RequestMapping("add")
    protected ResultBean add(E entity) {
        return service.add(entity);
    }*/

    @RequestMapping("remove")
    @ResponseBody
    protected ResultBean remove(@RequestParam(required = true) I id) {
        return service.remove(id);
    }

    @RequestMapping("form")
    protected String form(@RequestParam(required = true) String id, Model model) {
        BaseEntity entity = service.get(id);
        model.addAttribute(entitySimpleName, entity);
        return viewPreview + "Form";
    }

    /*@RequestMapping("edit")
    protected ResultBean edit(E entity) {
        return service.edit(entity);
    }*/

}
