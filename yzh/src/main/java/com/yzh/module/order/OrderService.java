package com.yzh.module.order;

import com.yzh.common.base.BaseService;
import com.yzh.common.utils.ResultBean;
import com.yzh.common.utils.StringUtil;
import com.yzh.common.utils.UserUtil;
import com.yzh.module.book.Book;
import com.yzh.module.book.BookDao;
import com.yzh.module.order.item.Item;
import com.yzh.module.order.item.ItemDao;
import com.yzh.module.order.linkinfo.LinkInfo;
import com.yzh.module.order.linkinfo.LinkInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/20 13:47
 */
@Service
@Transactional(readOnly = true)
public class OrderService extends BaseService<OrderDao, Order, String> {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private LinkInfoDao linkInfoDao;

    @Transactional(readOnly = false, rollbackFor = {Exception.class})
    public ResultBean place(String linkinfoId, String itemIds, HttpServletRequest request) {
        String onlineUserId = UserUtil.getOrdinaryUser(request).getId();
        //  校验收货地址
        LinkInfo linkInfo = new LinkInfo();
        linkInfo.setUserId(onlineUserId);
        linkInfo.setId(linkinfoId);
        linkInfo = linkInfoDao.getByEntity(linkInfo);
        if (linkInfo == null)
            return new ResultBean(ResultBean.CODE_PARAM_ERROR, "收货地址错误");
        String orderId = StringUtil.UUID();
        BigDecimal totalPrice = new BigDecimal(0);
        //  需要修改状态的item集合
        List<Item> itemList = new ArrayList<>();
        //  校验订单项
        for (String itemId : itemIds.split(",")) {
            Item item = new Item();
            item.setType((byte) 0);
            item.setCreateBy(onlineUserId);
            Book book = new Book();
            book.setIsShelves((byte) 1);
            item.setId(itemId);
            item = itemDao.getByEntity(item);
            if (item == null || item.getNum() < 1)
                return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "数据错误");
            //  校验书籍状态
            book.setId(item.getBookId());
            book = bookDao.getByEntity(book);
            if (book == null)
                return new ResultBean(ResultBean.CODE_REQUEST_FORBIDDEN, "数据错误");
            BigDecimal bookPrice = book.getPrice();
            item.setBookPrice(bookPrice);
            item.setType((byte) 1);
            itemList.add(item);
            totalPrice = totalPrice.add(bookPrice.multiply(new BigDecimal(item.getNum())));
        }
        //  创建订单
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(onlineUserId);
        order.setCreateTime(new Date());
        order.setTotalPrice(totalPrice);
        order.setLinkInfo(linkInfo);
        dao.insert(order);
        //  修改订单项状态     并    创建订单-订单项关联
        for (Item i : itemList) {
            itemDao.update(i);
            dao.addCoon(orderId, i.getId());
        }
        return new ResultBean(ResultBean.CODE_SUCCESS, "支付成功，请等待发货");
    }

}
