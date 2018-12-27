package com.mny.share.javatest;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import okhttp3.internal.Util;

/**
 * Crate by E470PD on 2018/12/6
 */
public class JaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /*声明Utils工具包*/
        Utils.init(this);
    }
}
