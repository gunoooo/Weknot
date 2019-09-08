package com.example.weknot_android.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.network.comm.FeedComm
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File

class FeedViewModel(application: Application) : BaseViewModel<List<Feed>, Void, FeedComm>(application, FeedComm()) {

    var pictureFile: MutableLiveData<File> = MutableLiveData()
    var pictureUri: MutableLiveData<Uri> = MutableLiveData()

    var picture: MutableLiveData<MultipartBody.Part> = MutableLiveData()
    var comment: MutableLiveData<RequestBody> = MutableLiveData()

    fun createFeed() {
        addDisposable(comm.createFeed(token, picture.value!!, comment.value!!), baseObserver)
    }

    fun getFeeds() {
        addDisposable(comm.getFeeds(token), dataObserver)
    }
}