package com.mny.share.javatest.net;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;

import com.mny.share.javatest.R;
import com.mny.share.javatest.net.netcode.NetCodes;

public class TranslateActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TranslateActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
    }

    public void hello(View v) {
        NetCodes.getInstance().helloTranslate(this);
    }

    public void thank(View v) {
        NetCodes.getInstance().thankTranslate(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetCodes.getInstance().clearDisposable();
    }

}
