package com.example.weknot.main_page.activity.SignActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weknot.R;
import com.example.weknot.data.User;
import com.example.weknot.main_page.activity.MainActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegisterUserInfoActivity extends AppCompatActivity {

    private String userBirth;
    private String userPhoneNumber;
    private String certificationNumber;

    private Button createButton;
    private Button userBirthButton;

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

        initData();

        event();
    }

    private void initData() {

        userBirthButton = findViewById(R.id.userBirthButton);
        createButton = findViewById(R.id.createButton);
        userBirthInput = findViewById(R.id.userBirthInput);
        userPhoneNumberInput = findViewById(R.id.userPhoneNumberInput);
        certificationNumberInput = findViewById(R.id.certificationNumberInput);

        userBirth = userBirthInput.getText().toString();
        userPhoneNumber = userPhoneNumberInput.getText().toString();
        certificationNumber = certificationNumberInput.getText().toString();

        userBirthInput.setFocusable(false);
        userBirthInput.setClickable(false);
    }

    private void event() {

        clickEvent();
    }

    private void clickEvent() {

        create();
        requestPhoneNumber();
        checkCertification();
    }

    private void create() {

        createButton.setOnClickListener(v -> {

            if(!(userBirthInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if(!(certificationNumberInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "인증을 해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {

                userBirth = userBirthInput.getText().toString();
                userPhoneNumber = userPhoneNumberInput.getText().toString();
                certificationNumber = certificationNumberInput.getText().toString();

//                    user.setBirth(userBirth); #userBirth <= String / #setBirth(Date()); //error
                user.setPhoneNumber(userPhoneNumber);
//                    user.setcertificationNumber(certificationNumber);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        showBirthDialog();
    }

    private void requestPhoneNumber() {

        if(!(userPhoneNumberInput.getText().toString().length() > 0)) {

            Toast toast = Toast.makeText(getApplicationContext(), "휴대폰 번호를 입력해주세요", Toast.LENGTH_LONG);
            toast.show();
        }
        else {

            //TODO
        }
    }

    private void checkCertification() {

        if(!(certificationNumberInput.getText().toString().length() > 0)) {

            Toast toast = Toast.makeText(getApplicationContext(), "인증번호를 입력 해주세요", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {

            //TODO
        }
    }

    private void showBirthDialog() {

        GregorianCalendar calendar = new GregorianCalendar();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);

        findViewById(R.id.userBirthButton).setOnClickListener(v -> {

            new DatePickerDialog(RegisterUserInfoActivity.this, dateSetListener, year, month, day).show();
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // TODO Auto-generated method stub

            String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);

            Toast.makeText(RegisterUserInfoActivity.this, msg, Toast.LENGTH_SHORT).show();

            userBirthInput.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);
        }
    };
}
