package com.mny.share.javatest.net.net_utils.basereqmodel;

/**
 * 网络请求基础类
 */
public class HttpResult<T> {
    /*正常状态下的code通过code来判断是什么异常比如10000为正常操作*/
    /*public static final int NORMAL_10000 = 10000;
    private int code;
    private T date;
    private String message;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDate() {
        return date;
    }
    */
    /**
     * 判断是否正常状态
     *//*
    public boolean isNormal() {
        return NORMAL_10000 == code;
    }

    public void setDate(T date) {
        this.date = date;
    }*/

    //上述注释为正规操作 在这里只为了测试 同时还有修改BaseResponseBodyConverter 中的convert方法
    //此处status=1 是请求正确
    private int status;
    private T content;
    private int normal = 1;

    public boolean isNormal() {
        return normal == getStatus();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
