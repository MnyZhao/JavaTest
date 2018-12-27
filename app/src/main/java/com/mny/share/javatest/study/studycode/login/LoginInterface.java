package com.mny.share.javatest.study.studycode.login;


import com.mny.share.javatest.study.studycode.login.reqmodel.ReqLogin;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqRegister;
import com.mny.share.javatest.study.studycode.login.respmodel.RespLogin;
import com.mny.share.javatest.study.studycode.login.respmodel.RespRegister;

import io.reactivex.Observable;

/**
 * Crate by E470PD on 2018/12/7
 */
public interface LoginInterface {
    Observable<RespLogin> login(ReqLogin reqLogin);

    Observable<RespRegister> register(ReqRegister reqRegister);
}
