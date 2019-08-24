package com.example.weknot_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseActivity;
import com.example.weknot_android.databinding.LoginActivityBinding;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding> {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        loginObserve();

        clickEvent();
    }

    private void initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void loginObserve() {
        loginViewModel.getData().observe(this, loginData -> {
            loginViewModel.insertUser(loginData.getUser());
            loginViewModel.setToken(loginData.getToken());
            startActivity(new Intent(this, MainActivity.class));
        });

        loginViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    private void clickEvent() {
        binding.loginBtn.setOnClickListener(v -> {
            setRequest();
            loginViewModel.login();
        });
    }

    private void setRequest() {
        loginViewModel.request.setValue(new LoginRequest(binding.idText.getText().toString(), binding.pwText.getText().toString()));
    }

    @Override
    protected int layoutId() {
        return R.layout.login_activity;
    }
}
