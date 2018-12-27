package com.mny.share.javatest.study.studycode;


import android.content.Context;
import android.util.Log;

import com.mny.share.javatest.study.studycode.login.RxLoginCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 操作符 map
 * Map
 * #### Map
 * #### map是RxJava中最简单的一个变换操作符了, 它的作用就是对上游发送的每一个事件应用一个函数,
 * #### 使得每一个事件都按照指定的函数去变化. 用事件图表示如下:
 * FlatMap 实例取login 模块查看 注册完成之后登陆
 * ConcatMap
 */
public class RxMap {
    private String TAG = "RxMap";
    private static RxMap instance;

    private RxMap() {
    }

    ;

    public static RxMap getInstance() {
        if (instance == null) {
            instance = new RxMap();
        }
        return instance;
    }

    /**
     * map 对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化
     * 发送int 利用map转换成 String
     */
    public void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * flatMap
     * 将一个发送事件的上游Observable变换为多个发送事件的Observables，
     * 然后将它们发射的事件合并后放进一个单独的Observable里.
     * 简单理解就是每次都生成一个新的水管 Observable
     * 上游每发送一个事件, flatMap都将创建一个新的水管, 然后发送转换之后的新的事件,
     * 下游接收到的就是这些新的水管发送的数据.
     * 这里需要注意的是, flatMap并不保证事件的顺序,
     */
    public void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                //这里多加几个数据方便区分 正常应该是 1 1 1 2 2 2 3 3 3
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                //这种要多试几次才会出现
                return Observable.just("I am value " + integer).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }

    /**
     * 与flatmap的区别就是保证顺序
     */
    public void testConcattMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                //这里多加几个数据方便区分 正常应该是 1 1 1 2 2 2 3 3 3
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("I am value " + integer);
//                }
//                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);

                return Observable.just("I am value " + integer).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, s);
            }
        });
    }
}
