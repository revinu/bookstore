package com.yzh.module.order.item;

import com.yzh.common.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yzh
 * @date 2018/4/20 13:44
 */
@Repository
public interface ItemDao extends BaseDao<Item, String> {

    List<Item> cart(Item item);

    void deleteByBookId(String bookId);

}
