package com.example.weknot_android.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.network.comm.FeedComm
import com.example.weknot_android.view.navigator.FeedNavigator
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File

class FeedViewModel(application: Application) : BaseViewModel<List<Feed>, FeedNavigator>(application) {
    private val feedComm = FeedComm()

    var feedAdapter = FeedAdapter(application)

    fun getFeeds() {
        addDisposable(feedComm.getFeeds(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: List<Feed>) {
        feedAdapter.updateList(data)
    }

    override fun onRetrieveBaseSuccess(message: String) { }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}