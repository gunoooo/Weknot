package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SignComm;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.response.data.LoginData;

public class LoginViewModel extends BaseViewModel<LoginData, LoginRequest> {

    private SignComm signComm;

    protected LoginViewModel(Application application) {
        super(application);
        signComm = new SignComm();
    }

    public void login() {
        addDisposable(signComm.login(request.getValue()),getDataObserver());
    }
}
