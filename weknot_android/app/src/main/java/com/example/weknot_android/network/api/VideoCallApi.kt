package com.example.weknot_android.network.api

import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.response.Response
import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.POST

interface VideoCallApi {

    @POST("/requestCall")
    fun requestCall(@Header("Authorization") token: String): Single<retrofit2.Response<Response<VideoCall>>>
}