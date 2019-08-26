package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SignComm;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.response.data.LoginData;
import com.example.weknot_android.room.entity.user.User;

public class LoginViewModel extends BaseViewModel<LoginData, LoginRequest, User> {

    private SignComm signComm;

    public LoginViewModel(Application application) {
        super(application);
        signComm = new SignComm();
    }

    public void login() {
        addDisposable(signComm.login(request.getValue()),getDataObserver());
    }

    public void setToken(String token) {
        getToken().setToken(token);
    }

    public void getUser() {
        addDisposable(getRepository().getUser(), getEntityObserver());
    }

    public void insertUser(User user) {
        getRepository().userInsert(user);
    }

    public void updateUser(User user) {
        getRepository().userUpdate(user);
    }
}
