package com.mny.share.javatest.net.model;

import com.mny.share.javatest.net.api.FlowableTranslateInService;

/**
 * 4 SouceFactory  接口实现类获取 方便统一管理
 */
public class SouceFactory {
    private static SouceFactory instance;

    private SouceFactory() {
    }

    public static SouceFactory getInstance() {
        if (instance == null) {
            instance = new SouceFactory();
        }
        return instance;
    }

    private TranslateInterfaceSouce translateInterfaceSouce;
    private FlowableTransSouce flowableTransSouce;

    /**
     * 获取网络请求接口的实现类
     *
     * @return
     */
    public TranslateInterfaceSouce getTransInterfaceSouce() {
        if (translateInterfaceSouce == null) {
            translateInterfaceSouce = new TranslateInterfaceSouceImpl();
        }
        return translateInterfaceSouce;
    }

    /**
     * 获取背压方式的接口实现
     *
     * @return
     */
    public FlowableTransSouce getFlowableTranslate() {
        if (flowableTransSouce == null) {
            flowableTransSouce = new FlowableTransSouceImpl();
        }
        return flowableTransSouce;
    }

}
