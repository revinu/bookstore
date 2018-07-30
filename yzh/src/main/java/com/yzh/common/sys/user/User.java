package com.yzh.common.sys.user;

import com.yzh.common.base.BaseEntity;

/**
 * 用户类
 *
 * @author yzh
 * @date 2018/4/2 10:37
 */
public class User extends BaseEntity<String> {

    private String username;
    private String password;
    private short type; //  1:管理员   0:普通用户
    private String headImage;   //头像
    private String name;
    private byte sex;   //  0:男 1:女
    private String mobile;

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", headImage=" + headImage +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                '}';
    }

}
