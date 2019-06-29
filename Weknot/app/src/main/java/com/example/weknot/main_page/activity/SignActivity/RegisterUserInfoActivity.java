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
import com.example.weknot.api.SignApi;
import com.example.weknot.data.SuccessResult;
import com.example.weknot.data.User;
import com.example.weknot.main_page.activity.MainActivity;
import com.example.weknot.retrofit.MyRetrofit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserInfoActivity extends AppCompatActivity {

    private String userId;
    private String userPassword;
    private String userName;
    private long userBirth;
    private String userPhoneNumber;
    private String userGender;

    private String certificationNumber;

    private Button createButton;
    private Button userBirthButton;

    private EditText userBirthInput;
    private EditText userPhoneNumberInput;
    private EditText certificationNumberInput;

    private RadioGroup userGenderItem;

    private SignApi signApi;

    private Intent intent = getIntent();

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
        userGenderItem = findViewById(R.id.radioGroup);
        userBirthInput = findViewById(R.id.userBirthInput);
        userPhoneNumberInput = findViewById(R.id.userPhoneNumberInput);
        certificationNumberInput = findViewById(R.id.certificationNumberInput);

        userBirthInput.setFocusable(false);
        userBirthInput.setClickable(false);

        signApi = MyRetrofit.getRetrofit().create(SignApi.class);

        userId = intent.getExtras().getString("id");
        userPassword = intent.getExtras().getString("password");
        userName = intent.getExtras().getString("name");
    }

    private void event() {

        checkEvent();
        clickEvent();
    }

    private void checkEvent() {

        checkGender();
    }

    private void clickEvent() {

        requestPhoneNumber();
        checkCertification();

        clickCreate();
    }

    private void clickCreate() {

        createButton.setOnClickListener(v -> {

            if(!(userBirthInput.getText().toString().length() > 0)) {

                Toast toast = Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요", Toast.LENGTH_SHORT);
                toast.show();
            }
//            else if(!(certificationNumberInput.getText().toString().length() > 0)) {
//
//                Toast toast = Toast.makeText(getApplicationContext(), "인증을 해주세요", Toast.LENGTH_SHORT);
//                toast.show();
//            }
            else {

                userPhoneNumber = userPhoneNumberInput.getText().toString();
                certificationNumber = certificationNumberInput.getText().toString();

                signApi.register(userId,userPassword,userName,userBirth,userGender,userPhoneNumber).enqueue(new Callback<SuccessResult>() {
                    @Override
                    public void onResponse(Call<SuccessResult> call, Response<SuccessResult> response) {

                        SuccessResult result = response.body();

                        if (result.getResult().equals("success")) {

                            Toast.makeText(getApplicationContext(),"회원가입 성공!",Toast.LENGTH_LONG);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else {

                            System.out.println("문제 발생");
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResult> call, Throwable t) {

                    }
                });

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

    private void checkGender() {
        int genderGroupID = userGenderItem.getCheckedRadioButtonId();
        userGender = ((RadioButton)findViewById(genderGroupID)).getText().toString();

        userGenderItem.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton genderButton = (RadioButton)findViewById(checkedId);
            userGender = genderButton.getText().toString();
        });
    }

    private void showBirthDialog() {

        GregorianCalendar calendar = new GregorianCalendar();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);

        userBirthButton.setOnClickListener(v -> {

            new DatePickerDialog(RegisterUserInfoActivity.this, dateSetListener, year, month, day).show();
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Date date = new Date(year-1900, monthOfYear,dayOfMonth);
            userBirth = date.getTime();

            String msg = String.format("%d / %d / %d", year, monthOfYear + 1, dayOfMonth);

            Toast.makeText(RegisterUserInfoActivity.this, msg, Toast.LENGTH_SHORT).show();

            userBirthInput.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);
        }
    };
}
