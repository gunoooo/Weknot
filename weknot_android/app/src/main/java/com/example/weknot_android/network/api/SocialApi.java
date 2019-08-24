package com.example.weknot_android.network.api;

import com.example.weknot_android.network.response.Response;
import com.example.weknot_android.room.entity.user.Friend;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SocialApi {

    @GET("/friends")
    Single<retrofit2.Response<Response<List<Friend>>>> getFriends(@Header("Authorization") String token);
}
