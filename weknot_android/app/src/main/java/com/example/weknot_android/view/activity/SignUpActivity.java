package com.example.weknot_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseActivity;
import com.example.weknot_android.databinding.SignUpActivityBinding;
import com.example.weknot_android.network.request.SignUpRequest;
import com.example.weknot_android.viewmodel.SignUpViewModel;

public class SignUpActivity extends BaseActivity<SignUpActivityBinding> {

    private SignUpViewModel signUpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        observeSignUpViewModel();

        clickEvent();
    }

    private void initViewModel() {
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
    }

    private void observeSignUpViewModel() {
        signUpViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());

        signUpViewModel.getSuccessMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }

    private void clickEvent() {
        binding.signUpButton.setOnClickListener(v -> {
            setRequest();
            signUpViewModel.signUp();
        });
    }

    private void setRequest() {
        signUpViewModel.request.setValue(new SignUpRequest(binding.idText.getText().toString(), binding.pwText.getText().toString(),
                binding.nameText.getText().toString(), binding.birthText.getText().toString(), binding.genderText.getText().toString(),
                binding.phoneNumber.getText().toString()));
    }

    @Override
    protected int layoutId() {
        return R.layout.sign_up_activity;
    }
}
