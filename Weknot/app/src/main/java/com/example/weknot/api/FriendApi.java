package com.example.weknot.api;

import com.example.weknot.data.Friend;
import com.example.weknot.data.SuccessResult;
import com.example.weknot.network.requestmodel.FriendRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FriendApi {

    @FormUrlEncoded
    @GET("")
    Call<List<Friend>> getFriends(@Header("") String toekn);

    @FormUrlEncoded
    @GET("")
    Call<List<Friend>> getRequesters(@Field("id") String id);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> addFriend(@Body FriendRequest friendRequest);

    @FormUrlEncoded
    @POST("")
    Call<SuccessResult> checkFriend(@Field("userId") String userId, @Field("friendId") String friendId);

}
