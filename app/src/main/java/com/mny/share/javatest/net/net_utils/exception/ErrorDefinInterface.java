package com.mny.share.javatest.net.net_utils.exception;

/**
 * Crate by E470PD on 2018/12/7
 */
public interface ErrorDefinInterface {
    //网络错误
    void onNetError();

    //空异常
    void onNullError();

    //未知错误
    void unKnowException(Throwable t);

    //自定义异常
    void onApiException(ApiException e);
}
