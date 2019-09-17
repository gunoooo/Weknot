package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.repository.UserIdRepository
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.view.navigator.SplashNavigator

class SplashViewModel(application: Application) : BaseViewModel<User, SplashNavigator>(application) {
    private val signComm = SignComm()

    fun autoLogin() {
        addDisposable(signComm.autoLogin(token), dataObserver)
    }

    private fun insertUserId(id: String) {
        userId = id
    }

    override fun onRetrieveDataSuccess(data: User) {
        insertUserId(data.id)
        getNavigator().openMainActivity()
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}