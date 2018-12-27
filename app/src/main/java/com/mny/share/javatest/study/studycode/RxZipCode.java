package com.mny.share.javatest.study.studycode;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mny.share.javatest.net.net_utils.ToastUtils;
import com.mny.share.javatest.study.StudyActivity;
import com.mny.share.javatest.study.StudyModel.StudyTranslate;
import com.mny.share.javatest.study.studyinterface.HelloInterface;
import com.mny.share.javatest.study.studyretorfitcreate.GetRetorfit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * 通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件.
 * 它按照严格的顺序应用这个函数。它只发射与发射数据项最少的那个Observable一样多的数据。
 * 应用场景 当两个接口都请求完成时才能展示数据 可以用zip
 */
public class RxZipCode {
    private static String TAG = "RxZipCode";
    private static RxZipCode instance;

    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof InterruptedIOException) {
                    Log.e(TAG, "Io interrupted");
                }
            }
        });
    }

    private RxZipCode() {
    }

    ;

    public static RxZipCode getInstance() {
        if (instance == null) {
            instance = new RxZipCode();
        }
        return instance;
    }

    private Observable<String> getObservabe2() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(1000);
                Log.e(TAG, "emit A");
                emitter.onNext("A");

                Thread.sleep(1000);
                Log.e(TAG, "emit B");
                emitter.onNext("B");

                Thread.sleep(1000);
                Log.e(TAG, "emit C");
                emitter.onNext("C");


                Log.e(TAG, "emit complete2");
                emitter.onComplete();
            }
        });
    }

    private Observable<Integer> getObserverable1() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Thread.sleep(1000);
                Log.e(TAG, "emit 1");
                emitter.onNext(1);

                Thread.sleep(1000);
                Log.e(TAG, "emit 2");
                emitter.onNext(2);

                Thread.sleep(1000);
                Log.e(TAG, "emit 3");
                emitter.onNext(3);

                Thread.sleep(1000);
                Log.e(TAG, "emit 4");
                emitter.onNext(4);


                Log.e(TAG, "emit complete1");
                emitter.onComplete();

            }
        });
    }

    /**
     * 事件在同一线程
     */
    public void zipCode() {
        Observable.zip(getObserverable1(), getObservabe2(), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return s + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 事件在不同线程
     */
    public void zipCode1() {
        Observable.zip(getObserverable1().subscribeOn(Schedulers.io()),
                getObservabe2().subscribeOn(Schedulers.io()), new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return s + integer;
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 应用场景 请求两个接口当都成功时展示数据
     */
    public void zipNetTest(Context context) {
        /*声明接口*/
        HelloInterface helloInterface = GetRetorfit.getInstance().getRetorfit()
                .create(HelloInterface.class);
        Observable<StudyTranslate> observable1 = helloInterface
                .getCall()
                .subscribeOn(Schedulers.io());//io线程请求
        Observable<StudyTranslate> observable2 = helloInterface
                .getCall()
                .subscribeOn(Schedulers.io());//io线程请求
        Observable.zip(observable1, observable2, new BiFunction<StudyTranslate, StudyTranslate, List<StudyTranslate>>() {
            @Override
            public List<StudyTranslate> apply(StudyTranslate studyTranslate, StudyTranslate studyTranslate2) throws Exception {
                List<StudyTranslate> studyTranslates = new ArrayList<>();
                studyTranslates.add(studyTranslate);
                studyTranslates.add(studyTranslate2);

                return studyTranslates;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StudyTranslate>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "Subscribe");
                    }

                    @Override
                    public void onNext(List<StudyTranslate> value) {
                        Log.e(TAG, "Translate:" + value.get(0).content.out + value.get(1).content.out);
                        ToastUtils.show(context, value.get(0).content.out + value.get(1).content.out);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "Complete");
                    }
                });
    }
}
