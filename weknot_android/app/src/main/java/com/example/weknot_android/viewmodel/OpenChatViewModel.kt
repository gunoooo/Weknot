package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.network.comm.OpenChatComm
import com.example.weknot_android.network.request.OpenChatRequest
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter

class OpenChatViewModel(application: Application) : BaseViewModel<List<OpenChatRoom>>(application) {
    private val openChatComm = OpenChatComm()

    var request = MutableLiveData<OpenChatRequest>()

    var openChatAdapter = OpenChatAdapter()

    fun getChattingRooms() {
        addDisposable(openChatComm.getChattingRooms(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: List<OpenChatRoom>) {
        openChatAdapter.updateList(data)
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}