//package com.example.weknot_android.viewmodel;
//
//import android.app.Application;
//
//import com.example.weknot_android.base.BaseViewModel;
//import com.example.weknot_android.network.comm.SignComm;
//import com.example.weknot_android.network.request.SignUpRequest;
//
//public class SignUpViewModel extends BaseViewModel<,SignUpRequest> {
//
//    private SignComm signComm;
//
//    protected SignUpViewModel(Application application) {
//        super(application);
//
//    }
//
//    public void SignUp() {
//
//        addDisposable(signComm.signUp(), getBaseObserver());
//    }
//}
