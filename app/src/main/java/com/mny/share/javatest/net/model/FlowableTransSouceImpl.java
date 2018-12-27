package com.mny.share.javatest.net.model;

import com.mny.share.javatest.net.api.ApiService;
import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Flowable;

/**
 * RxJava 背压策略 Flowable
 */
public class FlowableTransSouceImpl implements FlowableTransSouce {

    @Override
    public Flowable<StudyTranslate> hello() {
        return ApiService.getInstance().getFlowableTrans().hello();
    }

    @Override
    public Flowable<StudyTranslate> thanks() {
        return ApiService.getInstance().getFlowableTrans().thanks();
    }
}
