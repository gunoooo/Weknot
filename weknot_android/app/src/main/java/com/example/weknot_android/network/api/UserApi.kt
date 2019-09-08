package com.example.weknot_android.network.api

import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.network.response.Response
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApi {

    @GET("/profile/{userId}")
    fun getProfile(@Header("Authorization") token: String, @Path("userId") userId: String): Single<retrofit2.Response<Response<Profile>>>
}