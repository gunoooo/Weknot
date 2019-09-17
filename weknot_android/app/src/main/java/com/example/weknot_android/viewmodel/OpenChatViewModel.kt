package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.OpenChat.OpenChatRoom
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.OpenChatComm
import com.example.weknot_android.network.request.OpenChatRequest
import com.example.weknot_android.view.navigator.OpenChatNavigator
import com.example.weknot_android.widget.recyclerview.adapter.OpenChatAdapter
import io.reactivex.observers.DisposableSingleObserver

class OpenChatViewModel(application: Application) : BaseViewModel<List<OpenChatRoom>, OpenChatNavigator>(application) {
    private val openChatComm = OpenChatComm()

    var request = MutableLiveData<OpenChatRequest>()

    var openChatAdapter = OpenChatAdapter(application)

    fun getChattingRooms() {
        addDisposable(openChatComm.getChattingRooms(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: List<OpenChatRoom>) {
        openChatAdapter.updateList(data)
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}