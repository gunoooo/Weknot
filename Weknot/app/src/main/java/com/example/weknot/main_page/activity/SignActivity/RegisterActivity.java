package com.example.weknot.main_page.activity.SignActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.api.SignApi;
import com.example.weknot.data.SuccessResult;
import com.example.weknot.data.User;
import com.example.weknot.retrofit.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private String userId;
    private String userPassword;
    private String userName;

    private Button validateButton;
    private Button nextButton;
    private TextView backButton;

    private EditText userNameInput;
    private EditText userIdInput;
    private EditText userPasswordInput;
    private EditText userPasswordInputCheck;

    private boolean validate = false;

    private SignApi signApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initData();

        setting();

        event();
    }

    private void initData() {

        userNameInput = findViewById(R.id.userNameInput);
        userIdInput = findViewById(R.id.userIdInput);
        userPasswordInput = findViewById(R.id.userPasswordInput);
        userPasswordInputCheck = findViewById(R.id.userPasswordInputCheck);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        validateButton = findViewById(R.id.overlapCheckButton);

        signApi = MyRetrofit.getRetrofit().create(SignApi.class);
    }

    private void setting() {

        userPasswordInputCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String password = userPasswordInput.getText().toString();
                String passwordCompare = userPasswordInputCheck.getText().toString();

                if(password.equals(passwordCompare)) {

                    userPasswordInput.setBackgroundColor(Color.GREEN);
                    userPasswordInputCheck.setBackgroundColor(Color.GREEN);
                }
                else {

                    userPasswordInput.setBackgroundColor(Color.RED);
                    userPasswordInputCheck.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        clickValidate();
        back();
        next();
    }

    private void clickValidate() {

        validateButton.setOnClickListener(v -> {

            userId = userIdInput.getText().toString();

            signApi.validate(userId).enqueue(new Callback<SuccessResult>() {
                @Override
                public void onResponse(Call<SuccessResult> call, Response<SuccessResult> response) {

                    validate = true;

                    userIdInput.setBackgroundColor(Color.GREEN);
                    validateButton.setBackgroundColor(Color.GREEN);

                    userIdInput.setClickable(false);
                }

                @Override
                public void onFailure(Call<SuccessResult> call, Throwable t) {

                }
            });
        });
    }

    private void back() {

        backButton.setOnClickListener(v -> {

            finish();
        });
    }

    private void next() {

        nextButton.setOnClickListener(v -> {

            if(!(userNameInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(!validate) {

                Toast toast = Toast.makeText(getApplicationContext(), "아이디 중복확인을 해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(!(userPasswordInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(!(userPasswordInputCheck.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {

                userName = userNameInput.getText().toString();
                userPassword = userPasswordInput.getText().toString();

                Intent intent = new Intent(getApplicationContext(), RegisterUserInfoActivity.class);

                intent.putExtra("id", userId);
                intent.putExtra("password", userPassword);
                intent.putExtra("name", userName);

                startActivityForResult(intent,RESULT_OK);
            }
        });
    }
}
