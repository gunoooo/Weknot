package com.example.weknot_android.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.network.comm.FeedComm
import com.example.weknot_android.view.navigator.FeedNavigator
import com.example.weknot_android.view.navigator.FeedWriteNavigator
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class FeedWriteViewModel(application: Application) : BaseViewModel<Any, FeedWriteNavigator>(application) {
    private val feedComm = FeedComm()

    var tempPictureUri: MutableLiveData<Uri> = MutableLiveData()
    var pictureFile: MutableLiveData<File> = MutableLiveData()
    var pictureUri: MutableLiveData<Uri> = MutableLiveData()
    var picture: MutableLiveData<MultipartBody.Part> = MutableLiveData()

    var commentText: MutableLiveData<String> = MutableLiveData()
    var comment: MutableLiveData<RequestBody> = MutableLiveData()

    fun createFeed() {
        addDisposable(feedComm.createFeed(token, picture.value!!, comment.value!!), baseObserver)
    }

    private fun setRequest() {
        val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), pictureFile.value!!)
        picture.value = MultipartBody.Part.createFormData("picture", pictureFile.value!!.name, requestFile)
        comment.value = RequestBody.create("text/plain".toMediaTypeOrNull(),commentText.value!!)
    }

    override fun onRetrieveDataSuccess(data: Any) { }


    override fun onRetrieveBaseSuccess(message: String) {

    }

    override fun onRetrieveError(throwable: Throwable) {

    }
}