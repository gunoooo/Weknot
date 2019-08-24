package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.SignComm;
import com.example.weknot_android.network.request.SignUpRequest;
import com.example.weknot_android.network.response.data.NoneData;

public class SignUpViewModel extends BaseViewModel<NoneData, SignUpRequest> {

    private SignComm signComm;

    protected SignUpViewModel(Application application) {
        super(application);

        signComm = new SignComm();
    }

    public void SignUp() {

        addDisposable(signComm.signUp(request.getValue()), getBaseObserver());
    }
}
