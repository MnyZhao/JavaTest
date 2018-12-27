package com.mny.share.javatest.net.model;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Flowable;

/**
 * RxJava 背压策略 Flowable
 */
public interface FlowableTransSouce {
    Flowable<StudyTranslate> hello();

    Flowable<StudyTranslate> thanks();
}
