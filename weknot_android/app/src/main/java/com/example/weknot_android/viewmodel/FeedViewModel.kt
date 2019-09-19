package com.example.weknot_android.viewmodel

import android.app.Application
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.network.comm.FeedComm
import com.example.weknot_android.view.navigator.FeedNavigator
import com.example.weknot_android.widget.SingleLiveEvent
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class FeedViewModel(application: Application) : BaseViewModel<List<Feed>>(application) {
    private val feedComm = FeedComm()

    var feedAdapter = FeedAdapter()

    val openFeedWrite: SingleLiveEvent<Any> = SingleLiveEvent()

    fun getFeeds() {
        addDisposable(feedComm.getFeeds(token), dataObserver)
    }

    fun onClickWrite() {
        openFeedWrite.call()
    }

    override fun onRetrieveDataSuccess(data: List<Feed>) {
        feedAdapter.updateList(data)
    }

    override fun onRetrieveBaseSuccess(message: String) { }
}