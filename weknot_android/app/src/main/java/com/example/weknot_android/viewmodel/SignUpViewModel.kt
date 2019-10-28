package com.example.weknot_android.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.widget.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.*

class SignUpViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val signComm = SignComm()

    val request = SignUpRequest()

    val tempPictureUri: MutableLiveData<Uri> = MutableLiveData()
    val pictureUri: MutableLiveData<Uri> = MutableLiveData()
    private val pictureFile: MutableLiveData<File> = MutableLiveData()
    private val picture: MutableLiveData<MultipartBody.Part> = MutableLiveData()
    private val id: MutableLiveData<RequestBody> = MutableLiveData()
    private val pw: MutableLiveData<RequestBody> = MutableLiveData()
    private val name: MutableLiveData<RequestBody> = MutableLiveData()
    private val birth: MutableLiveData<RequestBody> = MutableLiveData()
    private val gender: MutableLiveData<RequestBody> = MutableLiveData()
    private val phoneNumber: MutableLiveData<RequestBody> = MutableLiveData()
    private val intro: MutableLiveData<RequestBody> = MutableLiveData()

    val onSuccessEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val signUpEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val openLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goToAlbum: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goToCrop: SingleLiveEvent<Unit> = SingleLiveEvent()
    val backMessageToast: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun signUp() {
        setRequest()
        fbSignUp()
        addDisposable(signComm.signUp(picture.value!!, id.value!!, pw.value!!,
                name.value!!, birth.value!!, gender.value!!, phoneNumber.value!!, intro.value!!), baseObserver)
    }

    private fun fbSignUp() {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(request.id + "@ryan.com", request.pw + "111111")
    }

    fun savePickData(data: Intent) {
        tempPictureUri.value = data.data
    }

    fun cropImage() {
        createFile()
        goToCrop.call()
    }

    private fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/Weknot/Profile")
        if (!file.exists()) file.mkdirs()
        pictureFile.value = File(Environment.getExternalStorageDirectory().toString() + "/Weknot/Profile" + Random().nextInt(999999999).toString() + ".jpg")
        try {
            pictureFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        pictureUri.value = Uri.fromFile(pictureFile.value)
    }

    private fun setRequest() {
        val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), pictureFile.value!!)
        picture.value = MultipartBody.Part.createFormData("photo", pictureFile.value!!.name, requestFile)
        id.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.id)
        pw.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.pw)
        name.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.name)
        birth.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.birth)
        gender.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.gender)
        phoneNumber.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.phoneNumber)
        intro.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.intro)
    }

    fun deleteFile() {
        tempPictureUri.value = null
        pictureFile.value = null
        pictureUri.value = null
        backMessageToast.call()
    }

    fun onClickPhoto() {
        goToAlbum.call()
    }

    fun onClickSignUp() {
        signUpEvent.call()
    }

    override fun onRetrieveBaseSuccess(message: String) {
        onSuccessEvent.value = message
        openLogin.call()
    }
}