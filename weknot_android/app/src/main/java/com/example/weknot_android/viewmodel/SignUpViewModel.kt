package com.example.weknot_android.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.viewmodel.BaseViewModel
import com.example.weknot_android.model.entity.user.FbUser
import com.example.weknot_android.network.comm.SignComm
import com.example.weknot_android.network.request.SignUpRequest
import com.example.weknot_android.widget.SingleLiveEvent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.util.*

class SignUpViewModel(application: Application) : BaseViewModel<Any>(application) {
    private val signComm = SignComm()

    var request = MutableLiveData<SignUpRequest>()

    val tempPictureUri: MutableLiveData<Uri> = MutableLiveData()
    val pictureUri: MutableLiveData<Uri> = MutableLiveData()
    private val pictureFile: MutableLiveData<File> = MutableLiveData()
    private val picture: MutableLiveData<MultipartBody.Part> = MutableLiveData()

    val onSuccessEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val fbSignUpEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val signUpEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val openLogin: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goToAlbum: SingleLiveEvent<Unit> = SingleLiveEvent()
    val goToCrop: SingleLiveEvent<Unit> = SingleLiveEvent()
    val backMessageToast: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun signUp() {
        setRequest()
        fbSignUpEvent.call()
        addDisposable(signComm.signUp(request.value!!), baseObserver)
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
        picture.value = MultipartBody.Part.createFormData("picture", pictureFile.value!!.name, requestFile)
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