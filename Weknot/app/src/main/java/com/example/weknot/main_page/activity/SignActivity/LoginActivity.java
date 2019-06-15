package com.example.weknot.main_page.activity.SignActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.api.SignApi;
import com.example.weknot.data.Result;
import com.example.weknot.retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SignApi signApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    private void initData() {

        signApi = MyRetrofit.getRetrofit().create(SignApi.class);
    }

    private void test() {

        signApi.login("a", "a").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Result result = response.body();

                if(result.getResult().equals("success")) {

                    System.out.println(result + "Asdfadfasdfsfsdfafd");
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
