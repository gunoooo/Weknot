package com.example.weknot_android.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.comm.VideoCallComm
import com.example.weknot_android.util.Strings
import com.example.weknot_android.widget.SingleLiveEvent
import io.reactivex.observers.DisposableSingleObserver

class VideoCallViewModel(application: Application) : BaseViewModel<VideoCall>(application) {
    private val videoCallComm = VideoCallComm()

    val channel = MutableLiveData<String>()

    val userInfo: Bundle = Bundle()

    val connectVideoCall: SingleLiveEvent<VideoCall> = SingleLiveEvent()

    fun setUp() {
        getUser()
    }

    private fun requestCall() {
        addDisposable(videoCallComm.requestCall(token), dataObserver)
    }

    fun completeMatching() {
        addDisposable(videoCallComm.completeMatching(token), dataObserver)
    }

    fun deleteRoom() {
        addDisposable(videoCallComm.deleteRoom(channel.value!!), baseObserver)
    }

    private fun getUser() {
        addDisposable(repository.getUser(userId), object : DisposableSingleObserver<User>() {
            override fun onSuccess(user: User) {
                userInfo.putString("displayName", user.name)
                userInfo.putString("email", user.phoneNumber)
                userInfo.putString("avatarURL", Strings.MAIN_HOST + "/image/" + user.picture)
            }

            override fun onError(e: Throwable) {
                onErrorEvent.call()
            }
        })
    }

    fun onClickMatching() {
        requestCall()
    }

    override fun onRetrieveDataSuccess(data: VideoCall) {
        connectVideoCall.value = data
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}