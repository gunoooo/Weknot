package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SignComm;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.response.data.LoginData;
import com.example.weknot_android.room.TokenManager;
import com.example.weknot_android.room.entity.user.User;
import com.example.weknot_android.room.repository.UserRepository;

public class LoginViewModel extends BaseViewModel<LoginData, LoginRequest> {

    private UserRepository repository;
    private TokenManager tokenManager;
    private SignComm signComm;

    protected LoginViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
        tokenManager = new TokenManager(application);
        signComm = new SignComm();
    }

    public void login() {
        addDisposable(signComm.login(request.getValue()),getDataObserver());
    }

    public void insertUser(User user) {
        repository.insert(user);
    }

    public void setToken(String token) {
        tokenManager.setToken(token);
    }
}
