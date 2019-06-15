package com.example.weknot.main_page.activity.SignActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.data.User;
import com.example.weknot.main_page.activity.MainActivity;

public class RegisterUserInfoActivity extends AppCompatActivity {

    private String userBirth;
    private String userPhoneNumber;
    private String certificationNumber;

    private Button createButton;

    private EditText userBirthInput;
    private EditText userPhoneNumberInput;
    private EditText certificationNumberInput;

    private RadioGroup userGender;

    private RadioButton manButton;
    private RadioButton womanButton;

    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_info);

        createButton = findViewById(R.id.createButton);
        userBirthInput = findViewById(R.id.userBirthInput);
        userPhoneNumberInput = findViewById(R.id.userPhoneNumberInput);
        certificationNumberInput = findViewById(R.id.certificationNumberInput);

        userBirth = userBirthInput.getText().toString();
        userPhoneNumber = userPhoneNumberInput.getText().toString();
        certificationNumber = certificationNumberInput.getText().toString();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(userBirthInput.getText().toString().length() > 0)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!(userPhoneNumberInput.getText().toString().length() > 0)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "아이디를 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                }
                /*else if(!(certificationNumberInput.getText().toString().length() > 0)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(!(userGender.getCheckedRadioButtonId().toString().length() > 0)) {

                    Toast toast = Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력해주세요", Toast.LENGTH_SHORT);
                    toast.show();
                }*/
                else {

                    userBirth = userBirthInput.getText().toString();
                    userPhoneNumber = userPhoneNumberInput.getText().toString();
                    certificationNumber = certificationNumberInput.getText().toString();

                    //user.setBirth(userBirth); #userBirth <= String / #setBirth(Date()); //error
                    user.setPhoneNumber(userPhoneNumber);
//                    user.setcertificationNumber(certificationNumber);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
