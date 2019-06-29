package com.example.weknot.api;

import com.example.weknot.data.LoginResult;
import com.example.weknot.data.SuccessResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignApi {

    @FormUrlEncoded
    @POST("/auth/login")
    Call<LoginResult> login(@Field("userId") String id, @Field("userPassword") String password);

    @FormUrlEncoded
    @POST("/auth/register")
    Call<SuccessResult> register(@Field("userId") String id, @Field("userPassword") String password,
                                 @Field("userName") String name, @Field("userBirth") long birth,
                                 @Field("userGender") String gender, @Field("userPhoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("/auth/checkUserId")
    Call<SuccessResult> validate(@Field("userId") String id);
}
