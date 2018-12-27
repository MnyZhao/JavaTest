package com.mny.share.javatest.net.api;

import com.mny.share.javatest.net.net_utils.RetrofitUtils;

import io.reactivex.BackpressureStrategy;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * ApiServie 用来提供 Retorfit的客户端
 * 并通过retorfit.create 创建interface的实现
 */
public class ApiService {
    private Retrofit retrofit;
    private String BASE_URL = "http://fy.iciba.com/";
    private static ApiService instance;

    private ApiService() {

    }

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    /**
     * 通过retorfit.create 创建interface的实现
     *
     * @return
     */
    public TranslateInterfaceService getTransService() {
        return getRetrofit().create(TranslateInterfaceService.class);
    }


    /**
     * 获取背压策略方式的 interface实现
     *
     * @return
     */
    public FlowableTranslateInService getFlowableTrans() {
        return getRetrofit().create(FlowableTranslateInService.class);
    }

    private Retrofit getRetrofit() {
        RetrofitUtils.Builder builder = new RetrofitUtils.Builder();
        builder.baseUrl(BASE_URL);
        builder.httpClient(new OkHttpClient.Builder().build());
        return builder.build();
    }

}
