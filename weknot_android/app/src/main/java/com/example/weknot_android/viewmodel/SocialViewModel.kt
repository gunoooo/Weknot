package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.network.comm.SocialComm

class SocialViewModel(application: Application) : BaseViewModel<List<Friend>, Void, SocialComm>(application, SocialComm()) {
    var receiveList = MutableLiveData<List<Friend>>()
    var friendList = MutableLiveData<List<Friend>>()

    val friends: Unit
        get() = addDisposable(comm.getFriends(token.token), dataObserver)
}