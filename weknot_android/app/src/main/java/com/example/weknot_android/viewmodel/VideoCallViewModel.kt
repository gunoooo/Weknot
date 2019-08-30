package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.comm.VideoCallComm

class VideoCallViewModel(application: Application) : BaseViewModel<VideoCall, Void, VideoCallComm>(application, VideoCallComm()) {

    fun requestCall() {
        addDisposable(comm.requestCall(token), dataObserver)
    }
}