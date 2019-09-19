package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.view.navigator.SplashNavigator
import com.example.weknot_android.widget.SingleLiveEvent

class SplashViewModel(application: Application) : BaseViewModel<User>(application) {
    private val signComm = SignComm()

    val openMain: SingleLiveEvent<Any> = SingleLiveEvent()

    fun autoLogin() {
        addDisposable(signComm.autoLogin(token), dataObserver)
    }

    private fun insertUserId(id: String) {
        userId = id
    }

    override fun onRetrieveDataSuccess(data: User) {
        insertUserId(data.id)
        openMain.call()
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}