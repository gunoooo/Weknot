package com.example.weknot.api;

import com.example.weknot.data.OpenChat;
import com.example.weknot.data.SuccessResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface OpenChatApi {

    // 아직 수정 안함

    @FormUrlEncoded
    @GET("")
    Call<List<OpenChat>> chattingRooms(@Field("id") String id);

    @FormUrlEncoded
    @GET("")
    Call<List<OpenChat>> getRequesters(@Field("id") String id);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> addFriend(@Field("userId") String userId, @Field("friendId") String friendId);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> checkFriend(@Field("userId") String userId, @Field("friendId") String friendId);

}
