package com.example.weknot_android.network.api;

import androidx.annotation.NonNull;

import com.example.weknot_android.network.request.LoginRequest;
import com.example.weknot_android.network.response.Response;
import com.example.weknot_android.network.response.data.LoginData;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignApi {

    @NonNull
    @POST("/auth/login")
    Single<retrofit2.Response<Response<LoginData>>> login(@Body LoginRequest loginRequest);

}
