package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.network.comm.SocialComm

class SocialViewModel(application: Application) : BaseViewModel<List<Friend>, Void, SocialComm>(application, SocialComm()) {
    var receiveList: ArrayList<Friend> = ArrayList()
    var friendList: ArrayList<Friend> = ArrayList()

    val friends: Unit
        get() = addDisposable(comm.getFriends(token), dataObserver)
}