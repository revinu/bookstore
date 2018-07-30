package com.yzh.module.order.item;

import com.yzh.common.base.BaseService;
import com.yzh.common.sys.user.User;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.UserUtil;
import com.yzh.module.book.Book;
import com.yzh.module.book.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/20 13:45
 */
@Service
@Transactional(readOnly = false)
public class ItemService extends BaseService<ItemDao, Item, String> {

    @Autowired
    private BookDao bookDao;

    @Transactional(readOnly = false)
    public ResultBean addToCart(String bookId, int num, HttpServletRequest request) {
        if (StringUtil.empty(bookId) || num <= 0)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR);
        Book book = bookDao.get(bookId);
        if (book == null)
            return new ResultBean(ResultBean.CODE_NOT_FOUND, "您所要购买的商品不存在");
        Item item = new Item();
        item.setCreateBy(UserUtil.getOrdinaryUser(request).getId());
        item.setBookId(bookId);
        Item existItem = dao.getByEntity(item);
        if (existItem != null) {
            existItem.setNum(existItem.getNum() + num);
            dao.update(existItem);
        } else {
            item.setNum(num);
            dao.insert(item);
        }
        return new ResultBean("加入购物车成功");
    }

    public ResultBean remove(String id,HttpServletRequest request) {
        Item item = new Item();
        item.setId(id);
        item.setCreateBy(UserUtil.getOrdinaryUser(request).getId());
        if (dao.getByEntity(item) != null)
            dao.delete(id);
        return super.remove(id);
    }

    public List<Item> cart(Item item, HttpServletRequest request) {
        User user = UserUtil.getOrdinaryUser(request);
        item.setCreateBy(user.getId());
        item.forbiddenPagination();
        return dao.cart(item);
    }

}
