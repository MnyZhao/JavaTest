package com.mny.share.javatest.net.net_utils.exception;

/**
 * Crate by E470PD on 2018/12/6
 */
public class ErrorControl {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1002;
    /**
     * DNS解析失败（无网络）
     */
    public static final int NO_NET_ERROR = 1003;
    /**
     * 连接超时错误
     */
    public static final int TIME_OUT_ERROR = 1004;
    /**
     * 网络（协议）错误
     */
    public static final int HTTP_ERROR = 1005;
    /**
     * 证书错误
     */
    public static final int SSL_ERROR = 1006;
    /**
     * 空指针
     */
    public static final int NULL_ERROR = 1007;
    /**
     * flatmap 异常
     */
    public static final int FLATMAP_ERROR = 1008;
}
