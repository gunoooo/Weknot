package com.example.weknot_android.network.comm

import com.example.weknot_android.base.BaseComm
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.network.api.SocialApi
import io.reactivex.Single

class SocialComm : BaseComm<SocialApi>() {

    fun getFriends(token: String): Single<List<Friend>> {
        return api.getFriends(token).map(getResponseObjectsFunction())
    }

    override fun type(): Class<SocialApi> {
        return SocialApi::class.java
    }
}