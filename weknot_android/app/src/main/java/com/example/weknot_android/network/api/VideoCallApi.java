package com.example.weknot_android.network.api;

import com.example.weknot_android.network.response.Response;
import com.example.weknot_android.network.response.data.LoginData;
import com.example.weknot_android.room.entity.videocall.VideoCall;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface VideoCallApi {

    @POST("/requestCall")
    Single<retrofit2.Response<Response<VideoCall>>> requestCall(@Header("Authorization") String token);
}
