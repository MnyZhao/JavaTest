package com.mny.share.javatest.net.model;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Observable;

/**
 * 2 声明网络请求的接口与api包中的TranslateInterfaceService 名称相同最好
 */
public interface TranslateInterfaceSouce {
    Observable<StudyTranslate> hello();

    Observable<StudyTranslate> thanks();

}
