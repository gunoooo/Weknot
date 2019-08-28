package com.example.weknot_android.network.comm

import com.example.weknot_android.base.BaseComm
import com.example.weknot_android.network.api.SignApi
import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.network.response.data.LoginData
import io.reactivex.Single
import retrofit2.Response

class SignComm : BaseComm<SignApi>() {

    fun login(loginRequest: LoginRequest): Single<LoginData> {
        return api.login(loginRequest).map(getResponseObjectsFunction())
    }

    fun signUp(signUpRequest: SignUpRequest): Single<String> {
        return api.signUp(signUpRequest).map(getResponseMessageFunction())
    }

    override fun type(): Class<SignApi> {
        return SignApi::class.java
    }
}