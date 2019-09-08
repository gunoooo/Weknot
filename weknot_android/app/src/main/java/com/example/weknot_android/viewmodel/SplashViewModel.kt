package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.repository.UserIdRepository
import com.example.weknot_android.network.comm.SignComm

class SplashViewModel(application: Application) : BaseViewModel<User, Void, SignComm>(application, SignComm()) {

    private val userIdRepository: UserIdRepository = UserIdRepository(application)

    fun autoLogin() {
        addDisposable(comm.autoLogin(token), dataObserver)
    }

    fun initUserId(id: String) {
        userIdRepository.setId(id)
    }
}