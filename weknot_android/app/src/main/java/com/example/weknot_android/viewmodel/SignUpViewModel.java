package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SignComm;
import com.example.weknot_android.network.request.SignUpRequest;
import com.example.weknot_android.room.entity.user.User;

public class SignUpViewModel extends BaseViewModel<Void, SignUpRequest, Void> {

    private SignComm signComm;

    public SignUpViewModel(Application application) {
        super(application);

        signComm = new SignComm();
    }

    public void signUp() {
        addDisposable(signComm.signUp(request.getValue()), getBaseObserver());
    }
}
