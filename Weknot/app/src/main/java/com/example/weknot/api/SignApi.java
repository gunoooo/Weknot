package com.example.weknot.api;

import com.example.weknot.data.Result;
import com.example.weknot.data.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignApi {

    @FormUrlEncoded
    @POST("")
    Call<Result> login(@Field("id") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("")
    Call<Result> register(@Field("user") User user);

//    @Field("id") String id, @Field("password") String password, @Field("name") String name,
//    @Field("birth")Date birth, @Field("gender") String gender, @Field("phoneNumber") String phoneNumber,
//    @Field("picture") String picture, @Field("intro") String intro, @Field("scope") String scope

    @FormUrlEncoded
    @POST("")
    Call<Result> validate(@Field("id") String id);
}
