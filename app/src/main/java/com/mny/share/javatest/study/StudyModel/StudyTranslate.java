package com.mny.share.javatest.study.StudyModel;

import android.util.Log;



/**
 * Crate by E470PD on 2018/12/5
 */
public class StudyTranslate {
    public int status;
    public StudyTranslate.content content;

    public class content {
        public String from;
        public String to;
        public String vendor;
        public String out;
        public int errNo;
    }

    public void show() {
        Log.d("RxJava", content.out);
    }
}
