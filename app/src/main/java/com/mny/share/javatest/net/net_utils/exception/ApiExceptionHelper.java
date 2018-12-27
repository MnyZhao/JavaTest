package com.mny.share.javatest.net.net_utils.exception;

import android.content.Context;

import com.mny.share.javatest.net.net_utils.ToastUtils;

/**
 * Crate by E470PD on 2018/12/6
 */
public class ApiExceptionHelper {
    private static ApiExceptionHelper instance;

    private ApiExceptionHelper() {
    }

    ;

    public static ApiExceptionHelper getInstance() {
        if (instance == null) {
            instance = new ApiExceptionHelper();
        }
        return instance;
    }

    /**
     * 网络链接异常
     */
    public void onNetError(Context context) {
        ToastUtils.show(context, "网络链接失败");
    }

    /**
     * 适配后台接口数据不一致的情况
     * "暂无数据"
     */
    public void onNullError(Context context) {
        ToastUtils.show(context, "暂无数据");
    }

    /**
     * 处理自定义的异常
     *
     * @param context
     * @param throwable
     */
    public void onApiException(Context context, ApiException throwable) {
        switch (throwable.getErrorCode()) {
            case 1:
                break;
            default:
                ToastUtils.show(context, throwable.getErrorMessage());
                break;
        }

    }
}
