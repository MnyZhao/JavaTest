package com.mny.share.javatest.study.studycode.login;

import com.mny.share.javatest.study.studycode.DError;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqLogin;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqRegister;
import com.mny.share.javatest.study.studycode.login.respmodel.RespLogin;
import com.mny.share.javatest.study.studycode.login.respmodel.RespRegister;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Crate by E470PD on 2018/12/7
 */
public class LoginModel implements LoginInterface {
    @Override
    public Observable<RespLogin> login(ReqLogin reqLogin) {
        //模拟本地登陆
        return Observable.create(new ObservableOnSubscribe<RespLogin>() {
            @Override
            public void subscribe(ObservableEmitter<RespLogin> e) throws Exception {
                if (reqLogin.name.equals("张三") && reqLogin.pwd.equals("123")) {
                    e.onNext(new RespLogin(reqLogin.name, reqLogin.pwd));
                    e.onComplete();
                } else {
                    e.onError(new DError(1, "LoginError"));
                }
            }
        });
    }

    @Override
    public Observable<RespRegister> register(ReqRegister reqRegister) {
        //模拟本地注册
        return Observable.create(new ObservableOnSubscribe<RespRegister>() {
            @Override
            public void subscribe(ObservableEmitter<RespRegister> e) throws Exception {
                if (reqRegister.name.equals("张三") &&
                        reqRegister.pwd.equals("123") &&
                        reqRegister.pwds.equals("123")) {
                    e.onNext(new RespRegister(reqRegister.name, reqRegister.pwd, reqRegister.pwds));
                    e.onComplete();
                } else {
                    e.onError(new DError(2, "RegisterError"));
                }
            }
        });
    }
}
