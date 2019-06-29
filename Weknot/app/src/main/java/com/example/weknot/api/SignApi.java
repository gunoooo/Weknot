package com.example.weknot.api;

import com.example.weknot.data.LoginResult;
import com.example.weknot.data.SuccessResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignApi {

    @FormUrlEncoded
    @POST("")
    Call<LoginResult> login(@Field("id") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> register(@Field("id") String id, @Field("password") String password,
                                 @Field("name") String name, @Field("birth") long birth,
                                 @Field("gender") String gender, @Field("phoneNumber") String phoneNumber,
                                 @Field("picture") String picture, @Field("intro") String intro,
                                 @Field("scope") String scope, @Field("point") int point);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> validate(@Field("id") String id);
}
