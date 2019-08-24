package com.example.weknot_android.network.comm;

import com.example.weknot_android.base.BaseComm;
import com.example.weknot_android.network.api.SignApi;
import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.request.SignUpRequest;
import com.example.weknot_android.network.response.data.LoginData;

import io.reactivex.Single;
import retrofit2.Response;

public class SignComm extends BaseComm<SignApi> {

    public Single<LoginData> login(LoginRequest loginRequest) {
        return api.login(loginRequest).map(getResponseObjectsFunction());
    }

    public Single<String> signUp(SignUpRequest signUpRequest) {
        return api.signUp(signUpRequest).map(Response::message);
    }
}
