package com.yzh.common.utils;

/**
 * @author yzh
 * @date 2018/1/16 22:52
 */
public class ResultBean {

    public static final short CODE_SUCCESS = 200;
    public static final short CODE_PARAM_ERROR = 201;
    public static final short CODE_NOT_FOUND = 202;
    public static final short CODE_UNKNOW_ERROR = 203;
    public static final short CODE_REQUEST_FORBIDDEN = 204;

    public static final String MSG_SUCCESS = "SUCCESS";
    public static final String MSG_PARAM_ERROR = "参数错误";
    public static final String MSG_NOT_FOUND = "操作对象不存在";
    public static final String MSG_UNKNOW_ERROR = "未知错误";
    public static final String MSG_REQUEST_FORBIDDEN = "请求失败";

    private short code = CODE_SUCCESS;
    private String msg;
    private Object data;

    public ResultBean() {}

    public ResultBean(short code) {
        this.code = code;
        switch (code) {
            case CODE_SUCCESS:
                this.msg = MSG_SUCCESS;
                break;
            case CODE_PARAM_ERROR:
                this.msg = MSG_PARAM_ERROR;
                break;
            case CODE_NOT_FOUND:
                this.msg = MSG_NOT_FOUND;
                break;
            case CODE_UNKNOW_ERROR:
                this.msg = MSG_UNKNOW_ERROR;
                break;
            case CODE_REQUEST_FORBIDDEN:
                this.msg = MSG_REQUEST_FORBIDDEN;
        }
    }

    public ResultBean(String msg) {
        this.msg = msg;
    }

    public ResultBean(Object data) {
        this.data = data;
    }

    public ResultBean(short code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(short code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultBean(short code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
