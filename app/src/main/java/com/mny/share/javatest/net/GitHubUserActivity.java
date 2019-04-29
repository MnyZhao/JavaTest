package com.mny.share.javatest.net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.mny.share.javatest.R;
import com.mny.share.javatest.net.net_utils.convert.BaseGsonConverterFactory;
import com.mny.share.javatest.study.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GitHubUserActivity extends AppCompatActivity implements View.OnClickListener {
    Button mBtnGet;
    private EditText mEtGetuser;
    private AutoCompleteTextView mTvMsg;
    Retrofit retrofit;
    StringBuffer buffer = new StringBuffer();

    public static void start(Context context) {
        Intent starter = new Intent(context, GitHubUserActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_user);
        initView();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.github.com/")
                .addConverterFactory(BaseGsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }

    private void initView() {
        mEtGetuser = (EditText) findViewById(R.id.et_getuser);
        mEtGetuser.setText("MnyZhao");
        mBtnGet = (Button) findViewById(R.id.btn_get);
        mTvMsg = (AutoCompleteTextView) findViewById(R.id.tv_msg);
        mBtnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_get:
                get(mEtGetuser.getText().toString().isEmpty() ? "MnyZhao" : mEtGetuser.getText().toString());
                break;
        }
    }

    public interface GitHubService {
        @GET("users/{user}")
        Call<String> listRepos(@Path("user") String user);

    }


    public void get(String userName) {
        buffer.append("开始\n");
        GitHubService service = retrofit.create(GitHubService.class);
        Call<String> call = service.listRepos(userName);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                buffer.append("内容:" + response.body() + "\n");
                buffer.append("结束:onResponse\n");
                mTvMsg.setText(buffer.toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                buffer.append("结束:onFailure\n" + t.getMessage());
                mTvMsg.setText(buffer.toString());
            }
        });
    }
}

class Info {
    private String id;
    private String node_id;
    private String name;
    private String full_name;

}