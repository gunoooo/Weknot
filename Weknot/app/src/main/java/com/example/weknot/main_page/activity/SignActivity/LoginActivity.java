package com.example.weknot.main_page.activity.SignActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.api.SignApi;
import com.example.weknot.data.LoginResult;
import com.example.weknot.main_page.activity.MainActivity;
import com.example.weknot.main_page.activity.SignActivity.find.ShowFindIdActivity;
import com.example.weknot.retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private SignApi signApi;

    private EditText idText;
    private EditText passwordText;

    private Button loginButton;
    private Button registerButton;
    private TextView forgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initData();

        event();
    }

    private void initData() {

        idText = findViewById(R.id.idInput);
        passwordText = findViewById(R.id.passwordInput);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgetButton = findViewById(R.id.forgetButton);

        signApi = MyRetrofit.getRetrofit().create(SignApi.class);
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {
        clickLoginButton();
        clickRegisterButton();
        clickForgetButton();
    }

    private void clickLoginButton() {

        String id = idText.getText().toString();
        String password = passwordText.getText().toString();

        loginButton.setOnClickListener(v -> signApi.login(id, password).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                LoginResult loginResult = response.body();

                if(loginResult.getResult().equals("success")) {

                    String token  = loginResult.getToken();
                    saveToken(token);

                    Toast.makeText(getApplicationContext(),"로그인 성공!",Toast.LENGTH_LONG);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        }));

    }

    private void clickRegisterButton() {

        registerButton.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });

    }

    private void clickForgetButton() {

        forgetButton.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), ShowFindIdActivity.class);
            startActivity(intent);
        });

    }

    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("userToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token",token);

        editor.commit();
    }
}
