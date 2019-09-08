package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.UserComm

class UserViewModel(application: Application) : BaseViewModel<Profile, User, UserComm>(application, UserComm()) {

    fun getProfile(userId: String) {
        addDisposable(comm.getProfile(token, userId), dataObserver)
    }

    fun getMyId() {
//        addDisposable(repository.getUser(), entityObserver)
    }
}