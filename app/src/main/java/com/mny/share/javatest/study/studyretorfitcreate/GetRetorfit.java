package com.mny.share.javatest.study.studyretorfitcreate;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Crate by E470PD on 2018/8/16
 */
public class GetRetorfit {
    private static GetRetorfit instance;

    public GetRetorfit() {

    }

    public static GetRetorfit getInstance() {
        if (instance == null) {
            instance = new GetRetorfit();
        }
        return instance;
    }

    Retrofit retrofit = null;

    public Retrofit getRetorfit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                    .build();
        }
        return retrofit;
    }
}
