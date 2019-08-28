package com.example.weknot_android.network.api

import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.network.response.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface SocialApi {

    @GET("/friends")
    fun getFriends(@Header("Authorization") token: String): Single<retrofit2.Response<Response<List<Friend>>>>
}