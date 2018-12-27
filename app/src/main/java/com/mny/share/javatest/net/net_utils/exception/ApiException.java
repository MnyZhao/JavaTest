package com.mny.share.javatest.net.net_utils.exception;

/**
 * 自定义异常接收类
 */
public class ApiException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public ApiException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
