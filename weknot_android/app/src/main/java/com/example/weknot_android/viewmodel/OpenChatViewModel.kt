package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.OpenChatComm
import com.example.weknot_android.network.request.OpenChatRequest
import io.reactivex.observers.DisposableSingleObserver

class OpenChatViewModel(application: Application) : BaseViewModel<List<OpenChatRoom>, Void, OpenChatComm>(application, OpenChatComm()) {
    var request = MutableLiveData<OpenChatRequest>()

    val chatRoomUsers = MutableLiveData<List<User>>()

    val chattingRooms: Unit
        get() = addDisposable(comm.getChattingRooms(token), dataObserver)

    fun createChattingRoom() {
        addDisposable(comm.createChattingRoom(token, request.value!!), baseObserver)
    }

    fun getChattingRoomUsers(roomNumber: String) {
        val observer: DisposableSingleObserver<List<User>> = object : DisposableSingleObserver<List<User>>() {
            override fun onSuccess(t: List<User>) {
                chatRoomUsers.value = t
            }

            override fun onError(e: Throwable) {
                errorMessage.value = e.message
            }
        }
        addDisposable(comm.getChattingRoomUsers(token, roomNumber), observer)
    }
}