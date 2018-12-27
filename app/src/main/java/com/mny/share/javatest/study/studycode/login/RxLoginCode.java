package com.mny.share.javatest.study.studycode.login;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.mny.share.javatest.net.net_utils.ToastUtils;
import com.mny.share.javatest.study.studycode.DError;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqLogin;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqRegister;
import com.mny.share.javatest.study.studycode.login.respmodel.RespLogin;
import com.mny.share.javatest.study.studycode.login.respmodel.RespRegister;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Crate by E470PD on 2018/12/7
 */
public class RxLoginCode {
    private String TAG = "RxLogin";
    private static RxLoginCode instance;

    private RxLoginCode() {
    }

    ;

    public static RxLoginCode getInstance() {
        if (instance == null) {
            instance = new RxLoginCode();
        }
        return instance;
    }

    LoginModel loginModel = new LoginModel();

    public void Login(Context context, String name, String pwd) {
        loginModel.login(new ReqLogin(name, pwd)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RespLogin>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespLogin value) {
                ToastUtils.show(context, value.name + "LoginSuccess");
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.show(context, ((DError) e).getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void Register(Context context, String name, String pwd) {
        loginModel.register(new ReqRegister(name, pwd, pwd)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RespRegister>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespRegister value) {
                ToastUtils.show(context, value.name + "RegisterSuccess");
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.show(context, ((DError) e).getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 注册成功后马上链式调用登陆
     *
     * @param context
     * @param reqRegister
     */
    public void registerAndLogin(Context context, ReqRegister reqRegister) {
        loginModel.register(reqRegister)
                .subscribeOn(Schedulers.io())//io 线程处理请求注册
                .observeOn(AndroidSchedulers.mainThread())//主线程中处理注册结果
                .doOnNext(new Consumer<RespRegister>() { //先根据注册的响应结果去做一些操作
                    @Override
                    public void accept(RespRegister s) throws Exception {
                        //先根据注册的响应结果去做一些操作
                        Log.e(TAG, s.name);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtils.show(context, ((DError) throwable).getMsg());
            }
        })
                .observeOn(Schedulers.io()) //回到IO线程去发起登录请求 因为subscribeOn只有
                // 第一次指定线程有效所以这里用observerOn
                .flatMap(new Function<RespRegister, Observable<RespLogin>>() {
                    @Override
                    public Observable<RespLogin> apply(RespRegister s) throws Exception {
                        ReqLogin reqLogin = new ReqLogin(s.name, s.pwd);
                        return loginModel.login(reqLogin);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<RespLogin>() {
                               @Override
                               public void accept(RespLogin respLogin) throws Exception {
                                   if ("张三".equals(respLogin.name)) {
                                       ToastUtils.show(context, "LoginSuccess");
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   ToastUtils.show(context, ((DError) throwable).getMsg());
                               }
                           }
                );
    }
}
