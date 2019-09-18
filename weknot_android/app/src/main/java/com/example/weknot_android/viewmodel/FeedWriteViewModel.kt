package com.example.weknot_android.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Environment
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
import java.io.IOException
import java.util.*

class FeedWriteViewModel(application: Application) : BaseViewModel<Any, FeedWriteNavigator>(application) {
    private val feedComm = FeedComm()

    var tempPictureUri: MutableLiveData<Uri> = MutableLiveData()
    var pictureUri: MutableLiveData<Uri> = MutableLiveData()
    private var pictureFile: MutableLiveData<File> = MutableLiveData()
    private var picture: MutableLiveData<MultipartBody.Part> = MutableLiveData()

    var commentText: MutableLiveData<String> = MutableLiveData()
    private var comment: MutableLiveData<RequestBody> = MutableLiveData()

    fun postFeed() {
        setRequest()
        addDisposable(feedComm.postFeed(token, picture.value!!, comment.value!!), baseObserver)
    }

    fun savePickData(data: Intent) {
        tempPictureUri.value = data.data
    }

    fun cropImage() {
        createFile()
        getNavigator().goToCropPage()
    }

    private fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/Weknot")
        if (!file.exists()) file.mkdirs()
        pictureFile.value = File(Environment.getExternalStorageDirectory().toString() + "/Weknot/" + Date(System.currentTimeMillis()) + ".jpg")
        try {
            pictureFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        pictureUri.value = Uri.fromFile(pictureFile.value)
    }

    private fun setRequest() {
        val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), pictureFile.value!!)
        picture.value = MultipartBody.Part.createFormData("picture", pictureFile.value!!.name, requestFile)
        comment.value = RequestBody.create("text/plain".toMediaTypeOrNull(),commentText.value!!)
    }

    fun deleteFile() {
        tempPictureUri.value = null
        pictureFile.value = null
        pictureUri.value = null
        getNavigator().toastBackMessage()
    }

    override fun onRetrieveDataSuccess(data: Any) { }

    override fun onRetrieveBaseSuccess(message: String) {
        getNavigator().openMainActivity()
    }

    override fun onRetrieveError(throwable: Throwable) {
        getNavigator().handleError(throwable)
    }
}