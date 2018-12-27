package com.mny.share.javatest.study.studyinterface;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Crate by E470PD on 2018/12/5
 */
public interface HelloInterface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<StudyTranslate> getCall();

}
