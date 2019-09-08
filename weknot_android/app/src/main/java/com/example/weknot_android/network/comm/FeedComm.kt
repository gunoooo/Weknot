package com.example.weknot_android.network.comm

import com.example.weknot_android.base.BaseComm
import com.example.weknot_android.network.api.FeedApi
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody

class FeedComm : BaseComm<FeedApi>() {

    fun createFeed(token: String, picture: MultipartBody.Part, comment: RequestBody): Single<String> {
        return api.createFeed(token, picture, comment).map(getResponseMessageFunction())
    }

    override fun type(): Class<FeedApi> {
        return FeedApi::class.java
    }
}