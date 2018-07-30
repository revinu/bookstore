package com.yzh.module.order;

import com.yzh.common.base.BaseEntity;
import com.yzh.module.order.item.Item;
import com.yzh.module.order.linkinfo.LinkInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yzh
 * @date 2018/4/20 13:38
 */
public class Order extends BaseEntity<String> {

    private String userId;
    private BigDecimal totalPrice;
    List<Item> itemList = new ArrayList<>();
    private LinkInfo linkInfo = new LinkInfo();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public LinkInfo getLinkInfo() {
        return linkInfo;
    }

    public void setLinkInfo(LinkInfo linkInfo) {
        this.linkInfo = linkInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", totalPrice=" + totalPrice +
                ", itemList=" + itemList +
                ", linkInfo=" + linkInfo +
                ", id=" + id +
                ", createTime=" + createTime +
                '}';
    }

}
