package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.comm.VideoCallComm
import com.example.weknot_android.view.navigator.VideoCallNavigator

class VideoCallViewModel(application: Application) : BaseViewModel<VideoCall, VideoCallNavigator>(application) {
    private val videoCallComm = VideoCallComm()

    fun requestCall() {
        addDisposable(videoCallComm.requestCall(token), dataObserver)
    }

    fun onClickMatching() {
        requestCall()
    }

    override fun onRetrieveDataSuccess(data: VideoCall) {
        getNavigator().connectVideoCall(data)
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}