package com.mny.share.javatest.study.studycode;

import android.database.Cursor;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 操作数据库
 */
public class RxDateBase {
//    public Observable<List<Record>> readAllRecords() {
       /* return Observable.create(new ObservableOnSubscribe<List<Record>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Record>> emitter) throws Exception {
                Cursor cursor = null;
                try {
                    cursor = getReadableDatabase().rawQuery("select * from " + TABLE_NAME, new String[]{});
                    List<Record> result = new ArrayList<>();
                    while (cursor.moveToNext()) {
                        result.add(Db.Record.read(cursor));
                    }
                    emitter.onNext(result);
                    emitter.onComplete();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());*/
//    }

}
