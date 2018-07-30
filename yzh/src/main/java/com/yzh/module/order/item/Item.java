package com.yzh.module.order.item;

import com.yzh.common.base.BaseEntity;
import com.yzh.module.book.Book;

import java.math.BigDecimal;

/**
 * @author yzh
 * @date 2018/4/20 13:39
 */
public class Item extends BaseEntity<String> {

    private String bookId;
    private BigDecimal bookPrice;
    private Book book;
    private int num;
    private BigDecimal totalPrice;
    private byte type;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item{" +
                "bookId='" + bookId + '\'' +
                ", bookPrice=" + bookPrice +
                ", book=" + book +
                ", num=" + num +
                ", totalPrice=" + totalPrice +
                ", type=" + type +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                '}';
    }

}
