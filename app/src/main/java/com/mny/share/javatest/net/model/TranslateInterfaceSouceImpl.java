package com.mny.share.javatest.net.model;

import com.mny.share.javatest.net.api.ApiService;
import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Observable;

/**
 * 3 实现网络请求接口并返回observerable
 */
public class TranslateInterfaceSouceImpl implements TranslateInterfaceSouce {
    public TranslateInterfaceSouceImpl() {
    }

    @Override
    public Observable<StudyTranslate> hello() {
        return ApiService.getInstance().getTransService().hello();
    }

    @Override
    public Observable<StudyTranslate> thanks() {
        return ApiService.getInstance().getTransService().thanks();
    }
}
