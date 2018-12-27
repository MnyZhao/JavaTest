package com.mny.share.javatest.study.studycode;

/**
 * Crate by E470PD on 2018/12/7
 */
public class DError extends RuntimeException {
    private int code;
    private String msg;

    public DError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
