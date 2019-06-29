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
import com.example.weknot.data.User;

public class RegisterActivity extends AppCompatActivity {

    private String userId;
    private String userPassword;
    private String userName;

    private Button nextButton;

    private TextView backButton;

    private EditText userNameInput;
    private EditText userIdInput;
    private EditText userPasswordInput;
    private EditText userPasswordInputCheck;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameInput = findViewById(R.id.userNameInput);
        userIdInput = findViewById(R.id.userIdInput);
        userPasswordInput = findViewById(R.id.userPasswordInput);
        userPasswordInputCheck = findViewById(R.id.userPasswordInputCheck);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {

            finish();
        });

        nextButton.setOnClickListener(v -> {

            if(!(userNameInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(!(userIdInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT);
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
                userId = userIdInput.getText().toString();
                userPassword = userPasswordInput.getText().toString();

                user.setId(userId);
                user.setPassword(userPassword);
                user.setName(userName);

                Intent intent = new Intent(getApplicationContext(), RegisterUserInfoActivity.class);
                startActivity(intent);
            }
        });

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
}
