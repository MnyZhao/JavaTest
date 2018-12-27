package com.mny.share.javatest.study;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mny.share.javatest.R;
import com.mny.share.javatest.study.studycode.RxMap;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MapActivity.class);
        context.startActivity(starter);
    }

    public void mapTest(View v) {
        RxMap.getInstance().testMap();
    }

    public void flatMapTest(View v) {
        RxMap.getInstance().testFlatMap();
    }

    public void concatMapTest(View v) {
        RxMap.getInstance().testConcattMap();
    }
}
