package com.yzh.module.order;

import com.yzh.common.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yzh
 * @date 2018/4/20 13:47
 */
@Repository
public interface OrderDao extends BaseDao<Order, String> {

    void addCoon(@Param("orderId") String orderId, @Param("itemId") String itemId);

}
