package com.example.weknot_android.network.api;

import com.example.weknot_android.network.request.OpenChatRequest;
import com.example.weknot_android.network.response.Response;
import com.example.weknot_android.room.entity.OpenChat.OpenChatRoom;
import com.example.weknot_android.room.entity.user.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpenChatApi {

    @GET("/chattingRooms")
    Single<retrofit2.Response<Response<List<OpenChatRoom>>>> getChattingRooms(@Header("Authorization") String token);

    @POST("/createChattingRoom")
    Single<retrofit2.Response<Response>> createChattingRoom(@Header("Authorization") String token,
                                                            @Body OpenChatRequest openChatRequest);

    @GET("/chattingRooms/{roomNumber}")
    Single<retrofit2.Response<Response<List<User>>>> getChattingRoomUsers(@Header("Authorization") String token,
                                                                      @Path("roomNumber") String roomNumber);
}
