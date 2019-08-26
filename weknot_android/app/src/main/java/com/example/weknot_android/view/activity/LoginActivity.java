package com.example.weknot_android.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.weknot_android.R;
import com.example.weknot_android.base.BaseActivity;
import com.example.weknot_android.databinding.LoginActivityBinding;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.room.entity.user.User;
import com.example.weknot_android.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity<LoginActivityBinding> {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        observeLoginViewModel();

        clickEvent();
    }

    private void initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void observeLoginViewModel() {

        loginViewModel.getErrorMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());

        loginViewModel.getData().observe(this, loginData -> {
            loginViewModel.setToken(loginData.getToken());
            loginViewModel.getUser();
        });

        loginViewModel.getEntity().observe(this, user -> {
            if (user == null) {
                loginViewModel.insertUser(loginViewModel.getData().getValue().getUser());
            }
            else {
                loginViewModel.updateUser(loginViewModel.getData().getValue().getUser());
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
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
