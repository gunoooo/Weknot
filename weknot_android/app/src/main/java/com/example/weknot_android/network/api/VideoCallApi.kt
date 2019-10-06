package com.example.weknot_android.network.api

import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.response.Response
import io.reactivex.Single
import retrofit2.http.*

interface VideoCallApi {

    @POST("/matching")
    fun requestCall(@Header("Authorization") token: String): Single<retrofit2.Response<Response<VideoCall>>>

    @GET("")
    fun completeMatching(@Header("Authorization") token: String): Single<retrofit2.Response<Response<Friend>>>

    @DELETE("/matching/{channel}")
    fun deleteRoom(@Path("channel") channel: String): Single<retrofit2.Response<Response<Any>>>
}