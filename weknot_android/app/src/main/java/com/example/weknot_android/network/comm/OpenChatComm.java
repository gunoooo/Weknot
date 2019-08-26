package com.example.weknot_android.network.comm;

import com.example.weknot_android.base.BaseComm;
import com.example.weknot_android.network.api.OpenChatApi;
import com.example.weknot_android.network.request.OpenChatRequest;
import com.example.weknot_android.room.entity.OpenChat.OpenChatRoom;
import com.example.weknot_android.room.entity.user.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class OpenChatComm extends BaseComm<OpenChatApi> {

    public Single<List<OpenChatRoom>> getChattingRooms(String token) {
        return api.getChattingRooms(token).map(getResponseObjectsFunction());
    }

    public Single<String> createChattingRoom(String token, OpenChatRequest openChatRequest) {
        return api.createChattingRoom(token, openChatRequest).map(Response::message);
    }

    public Single<List<User>> getChattingRoomUsers(String token, String roomNumber) {
        return api.getChattingRoomUsers(token, roomNumber).map(getResponseObjectsFunction());
    }

    @Override
    protected Class<OpenChatApi> type() {
        return OpenChatApi.class;
    }
}
