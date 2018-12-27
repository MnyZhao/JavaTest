package com.mny.share.javatest.study.studycode;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;
import com.mny.share.javatest.study.studyinterface.HelloInterface;
import com.mny.share.javatest.study.studyretorfitcreate.GetRetorfit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 网络请求 结合retorfit
 * 更加详细的结合版本请看 net 目录下的代码
 */
public class RxNet {
    public static final String TAG = "RxNet";


    private static RxNet instance;

    private RxNet() {
    }

    public static RxNet getInstance() {
        if (instance == null) {
            instance = new RxNet();
        }
        return instance;
    }

    public Disposable disposable;

    /**
     * 网络请求
     *
     * @param context
     */
    public void translateHello(Context context) {
        /*声明接口*/
        HelloInterface helloInterface = GetRetorfit.getInstance().getRetorfit()
                .create(HelloInterface.class);
        helloInterface
                .getCall()
                .subscribeOn(Schedulers.io())//io线程请求
                .observeOn(AndroidSchedulers.mainThread())//main线程处理
                .subscribe(new Observer<StudyTranslate>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(StudyTranslate value) {
                        Log.e(TAG, value.content.out);
                        value.show();
                        Toast.makeText(context, value.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 终止上下游之间的联系
     * 如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI, 那么APP肯定就崩溃了,
     * 怎么办呢, 上一节我们说到了Disposable , 说它是个开关, 调用它的dispose()方法时就会切断水管,
     * 使得下游收不到事件, 既然收不到事件, 那么也就不会再去更新UI了.
     * 因此我们可以在Activity中将这个Disposable 保存起来, 当Activity退出时, 切断它即可.
     * 那如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable,
     * 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候,
     * 调用CompositeDisposable.clear() 即可切断所有的水管.
     */
    public void disposable() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /**
     * 网络请求
     *
     * @param context
     */
    public void translateHello1(Context context) {
        /*声明接口*/
        HelloInterface helloInterface = GetRetorfit.getInstance().getRetorfit()
                .create(HelloInterface.class);
        helloInterface
                .getCall()
                .subscribeOn(Schedulers.io())//io线程请求
                .observeOn(AndroidSchedulers.mainThread())//main线程处理
                .subscribe(new Observer<StudyTranslate>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(StudyTranslate value) {
                        Log.e(TAG, value.content.out);
                        value.show();
                        Toast.makeText(context, value.content.out, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
