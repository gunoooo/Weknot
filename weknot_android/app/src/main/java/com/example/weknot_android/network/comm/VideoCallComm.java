package com.example.weknot_android.network.comm;

import com.example.weknot_android.base.BaseComm;
import com.example.weknot_android.network.api.VideoCallApi;
import com.example.weknot_android.room.entity.videocall.VideoCall;

import io.reactivex.Single;

public class VideoCallComm extends BaseComm<VideoCallApi> {

    public Single<VideoCall> requestCall(String token) {
        return api.requestCall(token).map(getResponseObjectsFunction());
    }
}
