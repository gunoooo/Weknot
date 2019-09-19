package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.widget.SingleLiveEvent

class SignUpViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val signComm = SignComm()

    var request = MutableLiveData<SignUpRequest>()

    val onSuccessEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val signUpEvent: SingleLiveEvent<Any> = SingleLiveEvent()
    val openLogin: SingleLiveEvent<Any> = SingleLiveEvent()

    fun signUp() {
        addDisposable(signComm.signUp(request.value!!), baseObserver)
    }

    fun onClickSignUp() {
        signUpEvent.call()
    }

    override fun onRetrieveDataSuccess(data: Any) { }

    override fun onRetrieveBaseSuccess(message: String) {
        onSuccessEvent.call()
        openLogin.call()
    }
}