package com.mny.share.javatest.net.net_utils.consumer;

import android.content.Context;
import android.net.ParseException;

import com.blankj.utilcode.util.Utils;
import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.mny.share.javatest.net.net_utils.ToastUtils;
import com.mny.share.javatest.net.net_utils.exception.ApiException;
import com.mny.share.javatest.net.net_utils.exception.ApiExceptionHelper;
import com.mny.share.javatest.net.net_utils.exception.ErrorDefinInterface;

import org.json.JSONException;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Crate by E470PD on 2018/12/6
 */
public abstract class ApiSubscriber<T> implements Subscriber<T>, ErrorDefinInterface {
    Context context;

    public ApiSubscriber() {
        context = Utils.getApp().getApplicationContext();
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof HttpException) {//"网络(协议)异常"
            onNetError();
        } else if (throwable instanceof UnknownHostException) {//"网络连接失败，请稍后重试"
            onNetError();
        } else if (throwable instanceof SocketTimeoutException) {// "连接超时"
            onNetError();
        } else if (throwable instanceof ConnectException) {//连接异常
            onNetError();
        } else if (throwable instanceof javax.net.ssl.SSLHandshakeException) {//证书验证失败
            onNetError();
        } else if (throwable instanceof JsonParseException ||
                throwable instanceof JSONException ||
                throwable instanceof ParseException) {//数据解析异常
            onNullError();
        } else if (throwable instanceof NullPointerException) {//暂无数据 flatmap 操作空数据
            onNullError();
        } else if (throwable instanceof ApiException) {//自定义异常
            onApiException((ApiException) throwable);
        } else {//未知错误
            unKnowException(throwable);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 统一的未知异常处理
     *
     * @param throwable
     */
    public void unKnowException(Throwable throwable) {
        ToastUtils.show(context, throwable.getMessage());
    }
    /**
     * 网络链接异常
     */
    @Override
    public void onNetError() {
        ApiExceptionHelper.getInstance().onNetError(context);
    }

    /**
     * 适配后台接口数据不一致的情况
     * "暂无数据"
     */
    @Override
    public void onNullError() {
        ApiExceptionHelper.getInstance().onNullError(context);
    }

    /**
     * 处理自定义的异常
     */
    @Override
    public void onApiException(ApiException throwable) {
        ApiExceptionHelper.getInstance().onApiException(context, throwable);
    }
}
