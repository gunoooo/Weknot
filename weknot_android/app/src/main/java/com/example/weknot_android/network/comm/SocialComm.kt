package com.example.weknot_android.network.comm

import com.example.weknot_android.base.BaseComm
import com.example.weknot_android.model.user.Friend
import com.example.weknot_android.network.api.SocialApi
import com.example.weknot_android.network.request.FriendAddRequest
import com.example.weknot_android.network.request.FriendRequest
import io.reactivex.Single

class SocialComm : BaseComm<SocialApi>() {

    fun getFriends(token: String): Single<List<Friend>> {
        return api.getFriends(token).map(getResponseObjectsFunction())
    }

    fun putFriend(token: String, friendRequest: FriendRequest): Single<String> {
        return api.putFriend(token, friendRequest).map(getResponseMessageFunction())
    }

    fun addFriend(token: String, receiver: String): Single<String> {
        return api.addFriend(token, FriendAddRequest(receiver)).map(getResponseMessageFunction())
    }

    override fun type(): Class<SocialApi> {
        return SocialApi::class.java
    }
}