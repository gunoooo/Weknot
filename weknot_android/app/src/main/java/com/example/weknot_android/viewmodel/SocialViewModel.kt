package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.user.Friend
import com.example.weknot_android.network.comm.SocialComm
import com.example.weknot_android.widget.recyclerview.adapter.SocialAdapter

class SocialViewModel(application: Application) : BaseViewModel<List<Friend>>(application) {
    private val socialComm = SocialComm()

    private var receiveList: ArrayList<Friend> = ArrayList()
    private var friendList: ArrayList<Friend> = ArrayList()

    var receiveAdapter: SocialAdapter = SocialAdapter()
    var friendAdapter: SocialAdapter = SocialAdapter()

    private val RECEIVE = 1
    private val FRIEND = 2

    fun getFriends() {
        addDisposable(socialComm.getFriends(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: List<Friend>) {
        for (friend in data) {
            if (friend.friendStatus == RECEIVE) {
                receiveList.add(friend)
            } else if (friend.friendStatus == FRIEND) {
                friendList.add(friend)
            }
        }
        receiveAdapter.updateList(receiveList)
        friendAdapter.updateList(friendList)
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}