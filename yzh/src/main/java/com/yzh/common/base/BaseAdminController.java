package com.yzh.common.base;

/**
 * @author yzh
 * @date 2018/4/11 22:20
 */
public class BaseAdminController<S extends BaseService, E extends BaseEntity, I> extends BaseController<S, E, I> {

    public BaseAdminController() {
        initEntitySimpleName();
        viewPreview = "admin/" + entitySimpleName + "/" + entitySimpleName;
    }

}
