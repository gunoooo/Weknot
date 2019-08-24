package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.response.data.LoginData;

public class LoginViewModel extends BaseViewModel<LoginData> {


    protected LoginViewModel(Application application) {
        super(application);
    }

    private void login(LoginRequest loginRequest) {
        addDisposable();
    }
}
