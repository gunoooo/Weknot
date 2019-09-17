package com.example.weknot_android.viewmodel

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.repository.UserIdRepository
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.response.data.LoginData
import com.example.weknot_android.view.navigator.LoginNavigator

class LoginViewModel(application: Application) : BaseViewModel<LoginData, LoginNavigator>(application) {
    private val signComm = SignComm()

    var request = LoginRequest()

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
        getNavigator().login()
    }

    fun onClickSignUp() {
        getNavigator().openSignUpActivity()
    }

    override fun onRetrieveDataSuccess(data: LoginData) {
        insertLoginData(data)
        getNavigator().openMainActivity()
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}