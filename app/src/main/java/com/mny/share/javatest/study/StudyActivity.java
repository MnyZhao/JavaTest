package com.mny.share.javatest.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.Utils;
import com.mny.share.javatest.R;
import com.mny.share.javatest.net.TranslateActivity;
import com.mny.share.javatest.study.studycode.RxCreate;
import com.mny.share.javatest.study.studycode.RxNet;
import com.mny.share.javatest.study.studycode.RxThread;
import com.mny.share.javatest.study.studycode.RxZipCode;
import com.mny.share.javatest.study.studycode.login.RxLoginCode;

import java.io.InterruptedIOException;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.internal.Util;

public class StudyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
    }

    public void onCreate(View v) {
        RxCreate.getInstance().createRx(false, false, false);
    }

    public void goTranslate(View v) {
        TranslateActivity.start(this);
    }

    public void onThreadBase(View v) {
        RxThread.getInstance().mainThread();
    }

    public void onThreadAsync(View view) {
        RxThread.getInstance().asyncNewThread();
    }

    /**
     * 上游subscribeOn多次指定只执行第一次
     * 下游observeOn指定几次切换几次线程
     * 会看到 RxCachedThreadScheduler-1 线程 其实是IO线程中的一个
     *
     * @param v
     */
    public void moreSetThread(View v) {
        RxThread.getInstance().moreThread();
    }

    /**
     * {@link #moreSetThread(View)} 详细的线程调度展示
     *
     * @param v
     */
    public void moreSetThreadInfo(View v) {
        RxThread.getInstance().moreThreadInfo();
    }

    public void translateHelloworld(View v) {
        RxNet.getInstance().translateHello(this);
    }

    public void GoMap(View v) {
        MapActivity.start(this);
    }

    public void GO_login(View v) {
        LoginActivity.start(this);
    }

    public void zipCode(View view) {
        RxZipCode.getInstance().zipCode();
    }

    public void zipCode1(View v) {
        RxZipCode.getInstance().zipCode1();
    }

    public void zipTest(View v) {
        RxZipCode.getInstance().zipNetTest(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         * 如果activity退出了我们要调用中断方法避免activity退出了更新UI 所出现的崩溃
         */
        RxNet.getInstance().disposable();
    }


}
