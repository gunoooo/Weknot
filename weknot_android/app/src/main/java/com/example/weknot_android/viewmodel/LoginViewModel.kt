package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.response.data.LoginData

class LoginViewModel(application: Application) : BaseViewModel<LoginData, Void, SignComm>(application, SignComm()) {
    var request = MutableLiveData<LoginRequest>()

    fun login() {
        addDisposable(comm!!.login(request.value!!), dataObserver)
    }

    fun setToken(token: String) {
        this.token!!.token = token
    }

    fun insertUser(user: User) {
        repository.insertUser(user)
    }
}