package com.mny.share.javatest.net.netcode;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mny.share.javatest.net.model.SouceFactory;
import com.mny.share.javatest.net.net_utils.consumer.ApiObserver;
import com.mny.share.javatest.net.net_utils.consumer.ApiSubscriber;
import com.mny.share.javatest.net.net_utils.exception.ApiException;
import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Crate by E470PD on 2018/12/5
 */
public class NetCodes {
    public static String TAG = "NetCodes";
    private static NetCodes instance;
    /*Rxjava用来管理disposable 做统一切断用 当activity退出*/
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private NetCodes() {
    }

    public static NetCodes getInstance() {
        if (instance == null) {
            instance = new NetCodes();
        }
        return instance;
    }

    ;

    public void helloTranslate(Context context) {
        /*SouceFactory.getInstance().getTransInterfaceSouce()
                .hello().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StudyTranslate>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(StudyTranslate value) {
                        Log.d(TAG, "VALUE:" + value.content.out);
                        value.show();
                        Toast.makeText(context, "VALUE:" + value.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "ERROE:" + e.getMessage());
                        Toast.makeText(context, "ERROR:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        SouceFactory.getInstance().getTransInterfaceSouce()
                .hello().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiObserver<StudyTranslate>() {
                    @Override
                    public void onNext(StudyTranslate value) {
                        value.show();
                        Toast.makeText(context, "VALUE:" + value.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onApiException(ApiException throwable) {
                        super.onApiException(throwable);
                    }

                    @Override
                    public void onNetError() {
                        super.onNetError();
                    }
                });
    }

    public void thankTranslate(Context context) {
        /*SouceFactory.getInstance().getFlowableTranslate()
                .thanks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StudyTranslate>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(StudyTranslate studyTranslate) {
                        Log.d(TAG, "VALUE:" + studyTranslate.content.out);
                        studyTranslate.show();
                        Toast.makeText(context, "VALUE:" + studyTranslate.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "ERROE:" + t.getMessage());
                        Toast.makeText(context, "ERROR:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
        SouceFactory.getInstance().getFlowableTranslate()
                .thanks().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<StudyTranslate>() {
                    @Override
                    public void onNext(StudyTranslate studyTranslate) {
                        Log.d(TAG, "VALUE:" + studyTranslate.content.out);
                        studyTranslate.show();
                        Toast.makeText(context, "VALUE:" + studyTranslate.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onApiException(ApiException throwable) {
                        super.onApiException(throwable);
                    }

                    @Override
                    public void onNetError() {
                        super.onNetError();
                    }
                });
    }

    /**
     * 切断上下游联系
     */
    public void clearDisposable() {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
    }
}
