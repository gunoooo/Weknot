package com.example.weknot.api;

import com.example.weknot.data.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

    @FormUrlEncoded
    @POST("")
    Call<User> getUser(@Field("id") String id);

}
