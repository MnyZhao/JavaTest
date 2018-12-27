package com.mny.share.javatest.net.api;

import com.mny.share.javatest.study.StudyModel.StudyTranslate;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 1
 * 创建网络请求接口 1 TranslateInterfaceService
 * 创建网络接口 2 TranslateInterfaceSouce
 * 实现接口 3 TranslateInterfaceSouceImpl
 * 创建工厂管理类 4 SouceFactory
 * 如果有多个不同类型的接口（TranslateInterfaceService，TranslateInterfaceService2）
 * 在这里这两个接口相关的实现都是相同的只是为了凸显factory作用
 * 可以创建这个SouceFactory类方便管理
 * 否则直接创建 impl 就可以实现管理
 */
public interface TranslateInterfaceService {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<StudyTranslate> hello();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=thanks%20world")
    Observable<StudyTranslate> thanks();
}
