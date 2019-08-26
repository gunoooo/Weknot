package com.example.weknot_android.viewmodel;

import android.app.Application;

import com.example.weknot_android.base.BaseViewModel;
import com.example.weknot_android.network.comm.VideoCallComm;
import com.example.weknot_android.room.entity.videocall.VideoCall;

public class VideoCallViewModel extends BaseViewModel<VideoCall, Void, Void> {

    private VideoCallComm videoCallComm;

    public VideoCallViewModel(Application application) {
        super(application);
        videoCallComm = new VideoCallComm();
    }

    public void requestCall() {
        addDisposable(videoCallComm.requestCall(getToken().getToken()),getDataObserver());
    }
}
