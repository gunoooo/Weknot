package com.example.weknot_android.network.api

import com.example.weknot_android.network.request.LoginRequest
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.network.response.Response
import com.example.weknot_android.network.response.data.LoginData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Single<retrofit2.Response<Response<LoginData>>>

    @POST("/auth/signUp")
    fun signUp(@Body signUpRequest: SignUpRequest): Single<retrofit2.Response<Response<Any>>>
}