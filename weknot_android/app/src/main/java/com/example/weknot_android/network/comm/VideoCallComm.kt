package com.example.weknot_android.network.comm

import com.example.weknot_android.base.BaseComm
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.model.entity.videocall.VideoCall
import com.example.weknot_android.network.api.VideoCallApi
import io.reactivex.Single

class VideoCallComm : BaseComm<VideoCallApi>() {

    fun requestCall(token: String): Single<VideoCall> {
        return api.requestCall(token).map(getResponseObjectsFunction())
    }

    fun completeMatching(token: String): Single<Friend> {
        return api.completeMatching(token).map(getResponseObjectsFunction())
    }

    fun deleteRoom(channel: String): Single<String> {
        return api.deleteRoom(channel).map(getResponseMessageFunction())
    }

    override fun type(): Class<VideoCallApi> {
        return VideoCallApi::class.java
    }
}