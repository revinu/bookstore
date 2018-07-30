package com.yzh.common.sys.dict;

import com.yzh.common.base.BaseEntity;
import org.apache.commons.lang3.Validate;

/**
 * 字典表
 *
 * @author yzh
 * @date 2018/4/12 11:24
 */
public class Dict extends BaseEntity<String> {

    private String type;    //  类型
    private Integer key;
    private String value;
    private int sortValue;  //  排序值

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        Validate.validState(key != null, "键为空");
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSortValue() {
        return sortValue;
    }

    public void setSortValue(int sortValue) {
        this.sortValue = sortValue;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "type='" + type + '\'' +
                ", key=" + key +
                ", value='" + value + '\'' +
                ", sortValue='" + sortValue + '\'' +
                ", id='" + id + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

}
