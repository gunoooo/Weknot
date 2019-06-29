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
    Call<LoginResult> login(@Field("id") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("/auth/register")
    Call<SuccessResult> register(@Field("id") String id, @Field("password") String password,
                                 @Field("name") String name, @Field("birth") long birth,
                                 @Field("gender") String gender, @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("/auth/checkUserId/wowjddl133")
    Call<SuccessResult> validate(@Field("id") String id);
}
