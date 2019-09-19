package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.response.data.LoginData
import com.example.weknot_android.widget.SingleLiveEvent

class LoginViewModel(application: Application) : BaseViewModel<LoginData>(application) {
    private val signComm = SignComm()

    var request = LoginRequest()

    val loginEvent: SingleLiveEvent<Any> = SingleLiveEvent()
    val openSignUp: SingleLiveEvent<Any> = SingleLiveEvent()
    val openMain: SingleLiveEvent<Any> = SingleLiveEvent()

    fun login() {
        addDisposable(signComm.login(request), dataObserver)
    }

    private fun insertLoginData(loginData: LoginData) {
        insertToken(loginData.token)
        insertUser(loginData.user)
        insertId(loginData.user.id)
    }

    private fun insertToken(token: String) {
        this.token = token
    }

    private fun insertUser(user: User) {
        repository.insertUser(user)
    }

    private fun insertId(id: String) {
        userId = id
    }

    fun onClickLogin() {
        loginEvent.call()
    }

    fun onClickSignUp() {
        openSignUp.call()
    }

    override fun onRetrieveDataSuccess(data: LoginData) {
        insertLoginData(data)
        openMain.call()
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}