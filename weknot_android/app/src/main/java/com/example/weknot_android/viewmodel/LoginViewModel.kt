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

class LoginViewModel(application: Application) : BaseViewModel<LoginData, Void, SignComm>(application, SignComm()) {
    var request = MutableLiveData<LoginRequest>()

    fun login() {
        addDisposable(comm.login(request.value!!), dataObserver)
    }

    fun insertLoginData(loginData: LoginData) {
        insertToken(loginData.token)
        insertUser(loginData.user)
        insertId(loginData.user.id)
    }

    fun insertToken(token: String) {
        this.token = token
    }

    fun insertUser(user: User) {
        repository.insertUser(user)
    }

    fun insertId(id: String) {
        userId = id
    }
}