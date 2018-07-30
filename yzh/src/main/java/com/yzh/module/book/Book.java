package com.yzh.module.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yzh.common.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品类
 *
 * @author yzh
 * @date 2018/4/10 22:41
 */
public class Book extends BaseEntity<String> {

    private String name;
    private BigDecimal price;
    private String image;
    private Date shelvesTime;   //  上架时间
    private Date publishDate;   //  发布日期
    private String author;  //  作者
    private byte isHot; //  是否热门
    private byte isShelves; //  是否上架
    private Integer category;  //  分类

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(Date shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte getIsHot() {
        return isHot;
    }

    public void setIsHot(byte isHot) {
        this.isHot = isHot;
    }

    public byte getIsShelves() {
        return isShelves;
    }

    public void setIsShelves(byte isShelves) {
        this.isShelves = isShelves;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", shelves=" + shelvesTime +
                ", publish=" + publishDate +
                ", author='" + author + '\'' +
                ", isHot=" + isHot +
                ", isShelves=" + isShelves +
                ", category=" + category +
                ", id=" + id +
                ", remark='" + remark + '\'' +
                '}';
    }

}
