package com.example.weknot.main_page.activity.SignActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.api.SignApi;
import com.example.weknot.data.LoginResult;
import com.example.weknot.main_page.activity.SignActivity.find.ShowFindIdActivity;
import com.example.weknot.retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SignApi signApi;

    private View registerButton;

    private TextView forgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = findViewById(R.id.registerButton);
        forgetButton = findViewById(R.id.forgetButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ShowFindIdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {

        signApi = MyRetrofit.getRetrofit().create(SignApi.class);
    }

    private void test() {

        signApi.login("a", "a").enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                LoginResult loginResult = response.body();

                if(loginResult.getResult().equals("success")) {

                    System.out.println(loginResult + "Asdfadfasdfsfsdfafd");
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }

    private class Button {

    }
}
