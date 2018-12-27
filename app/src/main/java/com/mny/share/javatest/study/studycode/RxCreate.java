package com.mny.share.javatest.study.studycode;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * onError onComplete
 * ObservableEmitter 发射器用来发送消息
 * 通过调用onNext onError onComplete 来发送事件
 * 上游 observable 可以无限发送onNext事件
 * 下游obsever 可以无限接受onNext事件
 * 当上游发送了onComplete 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
 * 当上游发送了onError 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
 * 不可同时发送多个onError onComplete
 * dispose
 * 调用dispose()并不会导致上游不再继续发送事件,
 * 上游会继续发送剩余的事件. 下游不再接收事件
 */
public class RxCreate {
    private static String TAG = "RxCreate";
    private static RxCreate instance;

    private RxCreate() {
    }

    public static RxCreate getInstance() {
        if (instance == null) {
            instance = new RxCreate();
        }
        return instance;
    }

    static Disposable disposable;

    /*简单的被观察者 观察者 订阅 关系的体现*/

    /**
     * isComplete 与isError 互斥 不能同时发送 也不能先后发送
     * onError 与onComplete 互斥 不能先发送onError在发送onComplete 反过来也是一样
     * 关于onComplete和onError唯一并且互斥这一点,  是需要自行在代码中进行控制,
     * 如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃.  比如发送多个onComplete
     * 是可以正常运行的, 依然是收到第一个onComplete就不再接收了,
     * 但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃
     *
     * @param isComplete 是否发送终止事件
     * @param isError    是否发送error事件
     * @param isDispose  是否终止上下游联系
     */
    public void createRx(boolean isComplete, boolean isError, boolean isDispose) {
        /*创建被观察者 上游*/
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //ObservableEmitter 发射器用来发送消息
                //通过调用onNext onError onComplete 来发送事件
                //上游 observable 可以无限发送onNext事件
                //下游obsever 可以无限接受onNext事件
                //当上游发送了onComplete 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
                //当上游发送了onError 事件后不会中断上游的事件发射 下游接收到oncomplete后不再接收事件
                //不可同时发送多个onError onComplete
                //onError 与onComplete 互斥 不能先发送onError在发送onComplete 反过来也是一样
                /*注: 关于onComplete和onError唯一并且互斥这一点,  是需要自行在代码中进行控制,
                    如果你的代码逻辑中违背了这个规则, 并不一定会导致程序崩溃.  比如发送多个onComplete
                    是可以正常运行的, 依然是收到第一个onComplete就不再接收了,
                    但若是发送多个onError, 则收到第二个onError事件会导致程序会崩溃.*/
                Log.e(TAG, "Emitter:" + "1");
                e.onNext("1");
                Log.e(TAG, "Emitter:" + "2");
                e.onNext("2");
                Log.e(TAG, "Emitter:" + "3");
                e.onNext("3");
                if (isError) {
                    e.onError(new Throwable());
                    Log.e(TAG, "Emitter OnError");
                }
                Log.e(TAG, "Emitter:" + "4");
                e.onNext("4");
                if (isComplete) {
                    e.onComplete();
                    Log.e(TAG, "Emitter onComplete");
                }
                Log.e(TAG, "Emitter:" + "5");
                e.onNext("5");
            }
        });

        /*创建观察者 下游*/
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //上游observable 与下游observer 通过onsubscribe链接
                //disposable调用dispose()会中断二者之间的联系但并不会导致上游不再继续发送事件,
                //上游会继续发送剩余的事件. 导致下游不再接收事件
                disposable = d;
                Log.e(TAG, "RxJavaCreate.onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if (value.equals("4")) {
                    //调用dispose()并不会导致上游不再继续发送事件,
                    // 上游会继续发送剩余的事件. 下游不再接收事件
                    if (isDispose) {
                        disposable.dispose();
                    }
                    Log.e(TAG, "RxJavaCreate.onNext:disposable" + disposable.isDisposed());
                }
                Log.e(TAG, "RxJavaCreate.onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "RxJavaCreate.onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "RxJavaCreate.onComplete");
            }
        };
        /*订阅事件*/
        observable.subscribe(observer);
    }
}
