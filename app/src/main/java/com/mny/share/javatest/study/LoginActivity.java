package com.mny.share.javatest.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mny.share.javatest.R;
import com.mny.share.javatest.net.net_utils.ToastUtils;
import com.mny.share.javatest.study.studycode.login.RxLoginCode;
import com.mny.share.javatest.study.studycode.login.reqmodel.ReqRegister;

public class LoginActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPwd;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);//张三
        mPwd = (EditText) findViewById(R.id.pwd);//123
    }

    public void Login(View v) {
        if (check()) {
            RxLoginCode.getInstance().Login(this, mName.getText().toString(), mPwd.getText().toString());
        } else {
            ToastUtils.show(this, "用户名密码不能为空");
        }
    }

    public void Register(View v) {
        if (check()) {
            RxLoginCode.getInstance().Register(this, mName.getText().toString(), mPwd.getText().toString());
        } else {
            ToastUtils.show(this, "用户名密码不能为空");
        }
    }

    public void RegisterLogin(View v) {
        if (check()) {
            ReqRegister reqRegister = new ReqRegister(mName.getText().toString(),
                    mPwd.getText().toString(), mPwd.getText().toString());
            RxLoginCode.getInstance().registerAndLogin(this, reqRegister);
        } else {
            ToastUtils.show(this, "用户名密码不能为空");
        }
    }

    private boolean check() {
        if (mName.getText().toString().isEmpty() || mPwd.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
}
