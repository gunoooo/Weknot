package com.example.weknot_android.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weknot_android.base.BaseViewModel
import com.example.weknot_android.model.entity.user.Profile
import com.example.weknot_android.model.entity.user.User
import com.example.weknot_android.model.repository.UserIdRepository
import com.example.weknot_android.network.comm.UserComm
import io.reactivex.observers.DisposableSingleObserver

class UserViewModel(application: Application) : BaseViewModel<Profile, User, UserComm>(application, UserComm()) {

    var id: MutableLiveData<String> = MutableLiveData()

    fun getProfile() {
        addDisposable(comm.getProfile(token, id.value!!), dataObserver)
    }

    fun getMyId(): String {
        return userId
    }
}