package com.mny.share.javatest.net.api;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * RxJava 背压策略 Flowable
 */
public interface FlowableTranslateInService {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Flowable<StudyTranslate> hello();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=thanks%20world")
    Flowable<StudyTranslate> thanks();
}
