package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.comm.VideoCallComm
import com.example.weknot_android.widget.SingleLiveEvent

class VideoCallViewModel(application: Application) : BaseViewModel<VideoCall>(application) {
    private val videoCallComm = VideoCallComm()

    val connectVideoCall: SingleLiveEvent<VideoCall> = SingleLiveEvent()

    fun requestCall() {
        addDisposable(videoCallComm.requestCall(token), dataObserver)
    }

    fun onClickMatching() {
        requestCall()
    }

    override fun onRetrieveDataSuccess(data: VideoCall) {
        connectVideoCall.value = data
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}