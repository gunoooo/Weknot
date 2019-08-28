package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.SignUpRequest

class SignUpViewModel(application: Application) : BaseViewModel<Void, Void, SignComm>(application, SignComm()) {
    var request = MutableLiveData<SignUpRequest>()

    fun signUp() {
        addDisposable(comm!!.signUp(request.value!!), baseObserver)
    }
}