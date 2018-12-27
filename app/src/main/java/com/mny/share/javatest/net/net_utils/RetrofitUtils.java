package com.mny.share.javatest.net.net_utils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mny.share.javatest.net.net_utils.convert.BaseGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 通过build 模式来构建retorfit
 */
public class RetrofitUtils {

    private Retrofit retrofit;
    private String baseUrl;
    private OkHttpClient okHttpClient;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public RetrofitUtils() {

    }

    public static class Builder {
        private RetrofitUtils retrofitUtils;

        public Builder() {
            retrofitUtils = new RetrofitUtils();
        }

        /**
         * 设置baseUrl
         *
         * @return
         */
        public Builder baseUrl(String baseUrl) {
            retrofitUtils.setBaseUrl(baseUrl);
            return this;
        }

        /**
         * 设置 okHttpClient
         *
         * @param okHttpClient
         * @return
         */
        public Builder httpClient(OkHttpClient okHttpClient) {
            retrofitUtils.setOkHttpClient(okHttpClient);
            return this;
        }

        public Retrofit build() {
            if (retrofitUtils.okHttpClient == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                retrofitUtils.okHttpClient = builder.build();
            }
            if (retrofitUtils.retrofit == null) {
                retrofitUtils.retrofit = new Retrofit.Builder()
                        .baseUrl(retrofitUtils.baseUrl)
                        .client(retrofitUtils.okHttpClient)
                        .addConverterFactory(BaseGsonConverterFactory.create()) //设置自定义Gson解析内部处理了异常
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                        .build();
            }
            return retrofitUtils.retrofit;
        }
    }
}
