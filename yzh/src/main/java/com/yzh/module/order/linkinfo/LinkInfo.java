package com.yzh.module.order.linkinfo;

import com.yzh.common.base.BaseEntity;

/**
 * @author yzh
 * @date 2018/4/22 8:25
 */
public class LinkInfo extends BaseEntity<String> {

    private String userId;
    private String address;
    private String zipCode; //  邮编
    private String mobile;
    private String name;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkInfo{" +
                "userId='" + userId + '\'' +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                ", createTime=" + createTime +
                '}';
    }

}
