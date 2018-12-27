package com.mny.share.javatest.study.studycode;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * subscribeOn 指定上游发送事件的线程
 * observeOn指定下游接收事件的线程
 * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效,
 * 其余的会被忽略.
 * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
 * <p>
 * doOnNext 在执行下一步操作之前要执行什么就在这里面写
 * 比如这里转到下一个指定线程之前打印线程名称
 * 在RxJava中, 已经内置了很多线程选项供我们选择, 例如有
 * <p>
 * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
 * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
 * Schedulers.newThread() 代表一个常规的新线程
 * AndroidSchedulers.mainThread()  代表Android的主线程
 */
public class RxThread {
    private static String TAG = "RxThread";
    private static RxThread instance;

    private RxThread() {
    }

    public static RxThread getInstance() {
        if (instance == null) {
            instance = new RxThread();
        }
        return instance;
    }

    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            Log.e(TAG, "Observable thread is : " + Thread.currentThread().getName());
            Log.e(TAG, "Emitter:" + 1);
            e.onNext(1);
        }
    });
    //事件消费者 类似obsever 如果我们不关心onError onComplete 中的数据 只关心onNext 可以用
    //Consumer 来代替
    Consumer<Integer> consumer = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) throws Exception {
            Log.e(TAG, "Obsever thread is : " + Thread.currentThread().getName());
            Log.e(TAG, "onNext:" + integer);
        }
    };

    /**
     * 主线程 不涉及切换线程
     */
    public void mainThread() {
        observable.subscribe(consumer);

    }

    public void asyncNewThread() {
        /**
         * subscribeOn 指定上游发送事件的线程
         * observeOn指定下游接收事件的线程
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效,
         * 其余的会被忽略.
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
         */
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    public void moreThread() {
        /**
         * subscribeOn 指定上游发送事件的线程
         * observeOn指定下游接收事件的线程
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效,
         * 其余的会被忽略.
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
         */
        observable
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(consumer);
        //会看到 RxCachedThreadScheduler-1 线程 其实是IO线程中的一个
    }

    /**
     * {@link #moreThread()} 的详情版本
     */
    public void moreThreadInfo() {
        /**
         * subscribeOn 指定上游发送事件的线程
         * observeOn指定下游接收事件的线程
         * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效,
         * 其余的会被忽略.
         * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
         *
         * doOnNext 在执行下一步操作之前要执行什么就在这里面写
         * 比如这里转到下一个指定线程之前打印线程名称
         *
         */
        observable
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);
        //会看到 RxCachedThreadScheduler-1 线程 其实是IO线程中的一个
    }
}
